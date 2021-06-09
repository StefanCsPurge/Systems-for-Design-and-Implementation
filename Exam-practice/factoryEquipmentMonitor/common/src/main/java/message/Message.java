package message;

import java.io.*;

public class Message {
    public static final String HOST = "localhost" ;
    public static final int PORT = 1234 ;
    public static final String OK = "ok";
    public static final String ERROR = "error";
    private String header, body;

    public Message(String header, String body) {
        this.header = header;   // name of the method to call on the server side (in case of client sending msg)
                                // the status of the operation (in case of the response from server)
        this.body = body;   // the arguments of the method from the server side (in case of client sending msg)
                            // the result of that operation (in case of the response from server)
    }

    public Message() {}

    public void writeTo(OutputStream os) throws IOException {
        String messageToPrint = header + System.lineSeparator() + body + System.lineSeparator();
        // System.out.println(messageToPrint);
        os.write(messageToPrint.getBytes());
    }

    public void readFrom(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder bufferMessage = new StringBuilder();
        do {
            //System.out.println(bufferMessage);
            bufferMessage.append(br.readLine());
            bufferMessage.append(System.lineSeparator());
        } while (br.ready());
        bufferMessage = new StringBuilder(bufferMessage.substring(0, bufferMessage.length() - System.lineSeparator().length()));
        String[] inputParsed = bufferMessage.toString().split(System.lineSeparator(), 2);

        header = inputParsed[0];
        if (inputParsed.length > 1) {
            body = inputParsed[1];
        }
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString(){
        return "<" + this.header + ">:<" + this.body + ">";
    }
}
