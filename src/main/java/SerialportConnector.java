import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.LinkedList;
import java.util.List;

public class SerialportConnector {
    private SerialPort serialPort = null;
    private String result = null;

    public SerialportConnector(int portnummer) {
        //konstruktør oprettes

        String[] portnames; //oprettelse af StringArray

        try {
            portnames = SerialPortList.getPortNames();//her hentes navnene til portene der er tilkoblet computeren
            serialPort = new SerialPort(portnames[portnummer]);//objektet serialPort tildeles den første port
            serialPort.openPort();//porten åbnes
            serialPort.setRTS(true);//klar til at sende(ReadyToSend = true)
            serialPort.setDTR(true);//klar til at modtage(DataToReceive = true)
            serialPort.setParams(115200, 8, 1, SerialPort.PARITY_NONE);//parametre bestemmes
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);//kontrolere flowet af data

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public List<Integer> getData() {//metoden oprettes
        try {
            if (serialPort.getInputBufferBytesCount() >= 12) {
                result = serialPort.readString();
                String[] rawValues;
                if (result != null && result.charAt(result.length() - 1) == ' ') ;
                result = result.substring(0, result.length() - 1);
                rawValues = result.split(" ");
                List<Integer> values = new LinkedList<>(); //istedet for int values, lav en EKG DTO med timestamp
                for (int i = 0; i < rawValues.length; i++) {
                    if (rawValues[i] != null && !rawValues[i].equals("")) {
                        values.add(Integer.parseInt(rawValues[i]));
                    }
                }
                return values;
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return null;
    }
}
        /*
        String[] rawValues = new String[10];//StringArray'et rawValues oprettes og længen bestemmes
        //initialisering af lokale variable
        int ir = 0;
        //int red = 0;
       // while (ir == 0 || red == 0) {//løkke oprettes
        while (ir == 0) {
            try {
                if (serialPort.getInputBufferBytesCount() >= 12) {//kontrolstruktur
                    result = serialPort.readString();
                    //System.out.println(result);//strengen aflæses og tildeles result

                    if (result != null && result.charAt(result.length() - 1) == ' ') {//result kontroleres
                        result = result.substring(0, result.length() - 1);
                        //System.out.println(result);//her fjernes det sidste index(#)
                        rawValues = result.split(" ");//nu splittes strengen og gemmes i et array'
                        //System.out.println(rawValues);
                    }

                    if (rawValues != null && rawValues.length >= 2) {//kontrollere om rawValues har nok indexer til konvertering
                        try {
                            ir = Integer.parseInt(rawValues[0]);
                                //System.out.println(ir);//0. index konverteres til Integer og tildeles ir
                            }catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
        //int[] returnArray = new int[]{ir};

           // System.out.println(ir);
            return ir;

    }
}

         */
                        /*
                        try {
                            red = Integer.parseInt(rawValues[1]);//1. index konverteres til Integer og tildeles red
                        } catch (Exception e) {//hvis der er et problem tildeles red værdien 0
                            red = 0;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int[] returnArray = new int[]{ir, red};//returnArray oprettes med ir som 0. index og red som 1. index
        return returnArray;//returnArray returneres

    */

