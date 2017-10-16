import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Evaluator implements Assignment1
{
    public void evaluate()
    {
        char matrix[][] = null;
        int fieldCount = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("minesweeper.txt"));
            String line;
            String file = "";

            while ((line = br.readLine()) != null)
            {
                if (Character.isDigit(line.charAt(0)))
                {
                    if (line.charAt(0) != '0')
                    {
                        matrix = new char[Character.getNumericValue(line.charAt(0))][Character
                                .getNumericValue(line.charAt(2))];

                        fieldCount++;
                        System.out.println("Field: " + fieldCount);

                        for (int i = 0; i < matrix.length; i++)
                        {
                            line = br.readLine();
                            matrix[i] = line.toCharArray();
                        }

                        for (int x = 0; x < matrix.length; x++)
                        {
                            for (int y = 0; y < matrix[x].length; y++)
                            {
                                file += getAdjacentBombs(matrix, x, y);
                                System.out.print(getAdjacentBombs(matrix, x, y));
                            }
                            System.out.println();
                        }
                    } else
                    {
                        writeToFile("output.txt", file);
                        System.out.print(Long.toString(minesweep(new File("output.txt"))));
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private String getAdjacentBombs(char[][] array, int xValue, int yValue)
    {
        int count = 0;

        if (isValidArray(array, xValue, yValue))
        {
            if (isBomb(array[xValue][yValue]))
            {
                return "*";
            } else
            {
                if (checkTopLeft(array, xValue, yValue))
                {
                    count++;
                }
                if (checkTop(array, xValue, yValue))
                {
                    count++;
                }
                if (checkTopRight(array, xValue, yValue))
                {
                    count++;
                }
                if (checkRight(array, xValue, yValue))
                {
                    count++;
                }
                if (checkBottomRight(array, xValue, yValue))
                {
                    count++;
                }
                if (checkBottom(array, xValue, yValue))
                {
                    count++;
                }
                if (checkBottomLeft(array, xValue, yValue))
                {
                    count++;
                }
                if (checkLeft(array, xValue, yValue))
                {
                    count++;
                }
                return Integer.toString(count);
            }
        }
        return "X";
    }

    @Override
    public long minesweep(File inputfile)
    {
        long count = 0;

        try
        {
            FileReader fr = new FileReader(inputfile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    if (Character.isDigit(line.charAt(i)))
                    {
                        count += Character.getNumericValue(line.charAt(i));
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return count;
    }

    private boolean isBomb(char character)
    {
        return character == '*';
    }

    private boolean isValidArray(char[][] array, int xValue, int yValue)
    {
        return (xValue >= 0 && xValue < array.length && yValue >= 0 && yValue < array[xValue].length);
    }

    private boolean checkTopLeft(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue - 1, yValue - 1))
        {
            return array[xValue - 1][yValue - 1] == '*';

        }
        return false;
    }

    private boolean checkTop(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue - 1, yValue))
        {
            return array[xValue - 1][yValue] == '*';
        }
        return false;
    }

    private boolean checkTopRight(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue - 1, yValue + 1))
        {
            return array[xValue - 1][yValue + 1] == '*';
        }
        return false;
    }

    private boolean checkRight(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue, yValue + 1))
        {
            return array[xValue][yValue + 1] == '*';
        }
        return false;
    }

    private boolean checkBottomRight(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue + 1, yValue + 1))
        {
            return array[xValue + 1][yValue + 1] == '*';
        }
        return false;
    }

    private boolean checkBottom(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue + 1, yValue))
        {
            return array[xValue + 1][yValue] == '*';
        }
        return false;
    }

    private boolean checkBottomLeft(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue + 1, yValue - 1))
        {
            return array[xValue + 1][yValue - 1] == '*';
        }
        return false;
    }

    private boolean checkLeft(char[][] array, int xValue, int yValue)
    {
        if (isValidArray(array, xValue, yValue - 1))
        {
            return array[xValue][yValue - 1] == '*';
        }
        return false;
    }

    private void writeToFile(String fileName, String out)
    {
        BufferedWriter outputWriter = null;
        try
        {
            outputWriter = new BufferedWriter(new FileWriter(fileName));
            outputWriter.write(out);
            outputWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
