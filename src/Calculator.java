public class Calculator {
    private String mode;
    private String serverPath;
    private int serverPort;

    public void runServer(String port,String operation ){
        try (Phone phone = new Phone(port)){
            while (true){
                System.out.println("Started Server with " + operation + " on " + port );
                phone.accept();
                String a = phone.readLine();
                String b = phone.readLine();
                int result = calculate(a,b,operation);
                String message = a + " " + operation + b + " = " + result;
                phone.writeLine(message);
                System.out.println("Accepted: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private int calculate(String a, String b, String operation) {
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        return switch (operation) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };
    }

    public void runClient(String path,String port,String a,String b){
        try (Phone phone = new Phone(path,port)){
            phone.writeLine(a);
            phone.writeLine(b);
            String answer = phone.readLine();
            System.out.println(answer);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
