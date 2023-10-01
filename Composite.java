import java.util.ArrayList;
import java.util.List;

// Interface que declara operações comuns para objetos simples e complexos de uma composição.
interface Graphic {
    void move(int x, int y);
    void draw();
    int getX();
    int getY();
}

// Classe folha que representa objetos finais de uma composição.
// Um objeto folha não pode ter sub-objetos.
class Dot implements Graphic {
    private int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void draw() {
        // Desenhar um ponto em x e y.
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}

// Todas as classes componente estendem outros componentes.
class Circle extends Dot {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public void draw() {
        // Desenhar um círculo em x e y com raio radius.
    }
}

// Classe composite que representa componentes complexos que podem ter filhos.
class CompoundGraphic implements Graphic {
    private List<Graphic> children = new ArrayList<>();

    public void add(Graphic child) {
        children.add(child);
    }

    public void remove(Graphic child) {
        children.remove(child);
    }

    public void move(int x, int y) {
        for (Graphic child : children) {
            child.move(x, y);
        }
    }

    public void draw() {
        // Para cada componente filho:
        // - Desenhe o componente.
        // - Atualize o retângulo limitante.
        // Desenhe um retângulo tracejado usando as limitantes.
    }

    @Override
    public int getX() {
        // Implementação fictícia para o Composite.
        return 0;
    }

    @Override
    public int getY() {
        // Implementação fictícia para o Composite.
        return 0;
    }
}

// Classe ImageEditor que trabalha com componentes através de suas interfaces base.
class ImageEditor {
    private CompoundGraphic all;

    public void load() {
        all = new CompoundGraphic();
        all.add(new Dot(1, 2));
        all.add(new Circle(5, 3, 10));
        // ...
    }

    public void groupSelected(Graphic[] components) {
        CompoundGraphic group = new CompoundGraphic();
        for (Graphic component : components) {
            group.add(component);
            all.remove(component);
        }
        all.add(group);
        // Todos os componentes serão desenhados.
        all.draw();
    }
}

public class Composite {
    public static void main(String[] args) {
        // Crie um editor de imagem.
        ImageEditor editor = new ImageEditor();

        // Carregue alguns objetos gráficos.
        editor.load();

        // Crie um novo Dot e Circle para adicionar ao Composite.
        Graphic newDot = new Dot(3, 4);
        Graphic newCircle = new Circle(8, 6, 15);

        // Adicione o novo Dot e Circle ao Composite.
        editor.groupSelected(new Graphic[]{newDot, newCircle});

        // Imprima os resultados da operação.
        System.out.println("Resultados da operação:");

        // Movendo os componentes e imprimindo sua posição.
        newDot.move(2, 2);
        newCircle.move(3, 3);

        System.out.println("Posição do novo Dot após mover: (" + newDot.getX() + ", " + newDot.getY() + ")");
        System.out.println("Posição do novo Circle após mover: (" + newCircle.getX() + ", " + newCircle.getY() + ")");
    }
}
