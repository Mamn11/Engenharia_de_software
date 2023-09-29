# Padrões de Design em Java para Engenharia de Software

Este repositório demonstra três padrões de design essenciais em Java: **Singleton**, **Composite** e **Observer**.

### Padrão Singleton

**Visão Geral:**

O padrão Singleton garante que uma classe tenha apenas uma instância e fornece um ponto global de acesso a essa instância. Isso é útil quando apenas uma instância de uma classe é necessária para coordenar ações em toda a aplicação.

**Implementação:**

```java
class Database {
    private static Database instance;

    private Database() { /* Construtor privado para evitar instâncias diretas */ }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void query(String sql) {
        // Lógica para execução de consultas no banco de dados
        // ...
    }
}
```

**Uso:**

```java
Database foo = Database.getInstance();
Database bar = Database.getInstance();
```

### Padrão Composite

**Visão Geral:**

O padrão Composite permite que objetos individuais e composições de objetos sejam tratados de maneira uniforme. É útil quando você precisa tratar objetos simples e composições de objetos da mesma forma.

**Implementação:**

```java
interface Graphic {
    void move(int x, int y);
    void draw();
    int getX();
    int getY();
}

class Dot implements Graphic { /* ... */ }
class Circle extends Dot { /* ... */ }

class CompoundGraphic implements Graphic { /* ... */ }

class ImageEditor { /* ... */ }
```

**Uso:**

```java
ImageEditor editor = new ImageEditor();
editor.load();
Graphic newDot = new Dot(3, 4);
Graphic newCircle = new Circle(8, 6, 15);
editor.groupSelected(new Graphic[]{newDot, newCircle});
newDot.move(2, 2);
newCircle.move(3, 3);
```

### Padrão Observer

**Visão Geral:**

O padrão Observer define uma dependência um-para-muitos entre objetos, permitindo que vários objetos observem e reajam a mudanças em um objeto.

**Implementação:**

```java
class EventManager { /* ... (ver código fornecido no exemplo) */ }
interface EventListener { /* ... (ver código fornecido no exemplo) */ }
class Editor { /* ... (ver código fornecido no exemplo) */ }
```

**Uso:**

```java
Editor editor = new Editor();
LoggingListener logger = new LoggingListener("/path/to/log.txt\n", "Alguém abriu o arquivo: %s");
editor.events.subscribe("open", logger);
EmailAlertsListener emailAlerts = new EmailAlertsListener("admin@example.com\n", "Alguém editou o arquivo: %s");
editor.events.subscribe("save", emailAlerts);
editor.openFile("/path/to/myfile.txt");
editor.saveFile();
```

Esses padrões são fundamentais para a Engenharia de Software, fornecendo estruturas flexíveis e reutilizáveis para diversos problemas comuns de design.
