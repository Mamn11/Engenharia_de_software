// A classe Database define um singleton para acesso a uma instância única de uma conexão de banco de dados
class Database {
    // O campo para armazenar a instância singleton deve ser declarado como estático.
    private static Database instance;

    // O construtor do singleton deve sempre ser privado para evitar chamadas diretas de construção com o operador `new`.
    private Database() {
        // Algum código de inicialização, como a conexão real com um servidor de banco de dados.
        // ...
    }

    // O método estático que controla o acesso à instância singleton.
    public static synchronized Database getInstance() {
        if (instance == null) {
            // Certifique-se de que a instância ainda não foi inicializada por outro thread enquanto este está aguardando pelo bloqueio.
            if (instance == null) {
                instance = new Database();
            }
        }
        return instance;
    }

    // Finalmente, qualquer singleton deve definir alguma lógica de negócio que pode ser executada em sua instância.
    public void query(String sql) {
        // Por exemplo, todas as consultas de banco de dados de um aplicativo passam por este método.
        // Portanto, você pode colocar lógica de otimização ou cache aqui.
        // ...
    }
}



public class Singleton {
    public static void main(String[] args) {
        // Obtenha instâncias do singleton Database
        Database foo = Database.getInstance();
        Database bar = Database.getInstance();

        // Verifique se foo e bar são a mesma instância
        if (foo == bar) {
            System.out.println("foo e bar são a mesma instância de Database.");
        } else {
            System.out.println("Erro: foo e bar não são a mesma instância de Database.");
        }

        // Execute consultas nos objetos foo e bar
        foo.query("SELECT * FROM tabela1");
        bar.query("UPDATE tabela2 SET coluna = 'valor' WHERE id = 1");
    }
}





