package chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Basic unit tests for the CLITest
 */
@RunWith(MockitoJUnitRunner.class)
public class CLITest {

    @Mock
    private PrintStream testOut;

    @Mock
    private InputStream testIn;

    /**
     * Make sure the CLI initially prints a welcome message
     */
    @Test
    public void testCLIWritesWelcomeMessage() {
        new CLI(testIn, testOut);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(testOut, times(1)).println(captor.capture());

        String message = captor.getValue();

        assertTrue("The CLI should initially print a welcome message", message.startsWith("Welcome"));
    }

    /**
     * Test that the CLI can initially accept input
     */
    @Test
    public void testHelpCommand() throws Exception {
        runCliWithInput("help");

        List<String> output = captureOutput();
        assertEquals("Should have 13 output calls", 13, output.size());
    }

    @Test
    public void testNewCommand() throws Exception {
        runCliWithInput("new");
        List<String> output = captureOutput();

        assertEquals("Should have had 6 calls to print strings", 6, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length()); //was 701, incorrect value
        assertEquals("It should have printed the board again", 721, output.get(4).length()); //was 701, incorrect value
    }

    @Test
    public void testBoardCommand() throws Exception {
        runCliWithInput("new", "board");
        List<String> output = captureOutput();
        int boardCount = countPrintedLines(output);

        assertEquals("Should have had 9 output calls", 9, output.size());
        assertEquals("It should have printed the board three times", output.get(2), output.get(4));  //this is not testing that it was output 3 times, its testing if the first and second time are equal
        assertEquals("It should actually have printed the board three times", boardCount, 3);
    }

    @Test
    public void testListCommand() throws Exception {
        runCliWithInput("list");
        List<String> output = captureOutput();

        assertNotNull("Should have some output for the new positions", output);
        assertEquals("Should have moves * characters for Pawn = 64 total.",64,output.get(4).length()); //once move validity is working, this will be 32 total + bishops
    }


    private int countPrintedLines(List<String> outputLines) {
        int boardCount=0;
        for (String line : outputLines)
            if (line.startsWith("\r\n"))
                boardCount++;
        return boardCount;
    }

    private List<String> captureOutput() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // 9 times means we printed Welcome, the input prompt twice, and the 'help' screen
        verify(testOut, atLeastOnce()).println(captor.capture());

        return captor.getAllValues();
    }

    private CLI runCliWithInput(String... inputLines) {
        StringBuilder builder = new StringBuilder();
        for (String line : inputLines) {
            builder.append(line).append(System.getProperty("line.separator"));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        CLI cli = new CLI(in, testOut);
        cli.startEventLoop();

        return cli;
    }
}
