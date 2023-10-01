import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe base do editor com gerenciamento de assinaturas
class EventManager {
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    // Método para inscrever um ouvinte em um tipo de evento
    public void subscribe(String eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    // Método para cancelar a inscrição de um ouvinte em um tipo de evento
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    // Método para notificar todos os ouvintes de um tipo de evento com dados
    public void notify(String eventType, String data) {
        List<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            for (EventListener listener : eventListeners) {
                listener.update(data);
            }
        }
    }
}

// Interface para os ouvintes
interface EventListener {
    void update(String filename);
}

// Classe do editor
class Editor {
    public EventManager events;
    private String file;

    public Editor() {
        events = new EventManager();
    }

    // Método para abrir um arquivo
    public void openFile(String path) {
        file = path;
        events.notify("open", path);
    }

    // Método para salvar um arquivo
    public void saveFile() {
        // Lógica de salvamento fictícia
        events.notify("save", file);
    }
}

// Ouvinte para registrar eventos em um arquivo de log
class LoggingListener implements EventListener {
    private String log;
    private String message;

    public LoggingListener(String logFilename, String message) {
        log = logFilename;
        this.message = message;
    }

    @Override
    public void update(String filename) {
        // Lógica de registro fictícia
        System.out.println("Log: " + message.replace("%s", filename));
    }
}

// Ouvinte para enviar alertas por e-mail
class EmailAlertsListener implements EventListener {
    private String email;
    private String message;

    public EmailAlertsListener(String email, String message) {
        this.email = email;
        this.message = message;
    }

    @Override
    public void update(String filename) {
        // Lógica de envio de e-mail fictícia
        System.out.println("Email enviado para " + email + ": " + message.replace("%s", filename));
    }
}

public class Observator {
    public static void main(String[] args) {
        // Configurar o editor e os ouvintes
        Editor editor = new Editor();

        LoggingListener logger = new LoggingListener("/path/to/log.txt\n", "Alguém abriu o arquivo: %s");
        editor.events.subscribe("open", logger);

        EmailAlertsListener emailAlerts = new EmailAlertsListener("admin@example.com\n", "Alguém editou o arquivo: %s");
        editor.events.subscribe("save", emailAlerts);

        // Simular abrir e salvar um arquivo
        editor.openFile("/path/to/myfile.txt");
        editor.saveFile();
    }
}
