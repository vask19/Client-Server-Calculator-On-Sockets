public class Main {
    public static void main(String[] args) {
        if (args.length < 3 ) {
            System.out.println("Usage: \n" +
                    "java Main server 8000 [/ * + -]\n" +
                    "java Main client 127.0.0.1 8000 15 42\n");
            return;
        }
        Calculator calculator = new Calculator();
        if (args[0].equals("server"))
            calculator.runServer(args[1],args[2]);

        if (args[0].equals("client"))
            calculator.runClient(args[1],args[2],args[3],args[4]);


    }
}
