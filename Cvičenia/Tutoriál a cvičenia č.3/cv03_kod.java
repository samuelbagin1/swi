public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(300, 300);
        Platno p;
        p = new Platno();

        GeomUtvar shape = new Stvorec(50, new Suradnica(100,100));
        p.addUtvar(shape);
        GeomUtvar shape2 = new Stvorec(50, new Suradnica(20,60));
        p.addUtvar(shape2);
        GeomUtvar shape3 = new Kruznica(30, new Suradnica(150,60));
        p.addUtvar(shape3);
        shape3.setFarba(Color.BLUE);

        frame.add("Center",p);
        frame.setVisible(true);
		p.repaint();
    }
}

abstract public class GeomUtvar implements Vykreslitelny {
    protected Suradnica hlavna_suradnica;
    protected Color farba;
    abstract public double obvod();
    abstract public double obsah();

    public void setHlavna_suradnica(Suradnica hlavna_suradnica) {
        this.hlavna_suradnica = hlavna_suradnica;
    }

    public Suradnica getHlavna_suradnica() {
        return hlavna_suradnica;
    }

    public Color getFarba() {
        return farba;
    }

    public void setFarba(Color farba) {
        this.farba = farba;
    }
}

public interface Vykreslitelny {
    public abstract void draw(Graphics2D g);
}

public class Suradnica {
    private int x;
    private int y;

    public Suradnica() {
    }

    public Suradnica(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Kruznica extends GeomUtvar{
    private int polomer;

    public Kruznica(int polomer, Suradnica suradnica) {
        super.hlavna_suradnica = suradnica;
        super.farba = Color.BLACK;
        this.polomer = polomer;
    }
    public Kruznica(int polomer, Suradnica suradnica, Color farba) {
        super.hlavna_suradnica = suradnica;
        super.farba = farba;
        this.polomer = polomer;
    }

    @Override
    public double obvod() {
        return 2*Math.PI*polomer;
    }

    @Override
    public double obsah() {
        return Math.PI*Math.pow(polomer,2.0);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(this.farba);
        g.fillOval(hlavna_suradnica.getX(), hlavna_suradnica.getY(), polomer, polomer);
    }

}

public class Stvorec extends GeomUtvar{
    private int dlzka;

    public Stvorec(int dlzka, Suradnica suradnica) {
        super.farba = Color.BLACK;
        this.dlzka = dlzka;
        this.hlavna_suradnica = suradnica;
    }

    public Stvorec(int dlzka, Suradnica suradnica, Color farba) {
        super.farba = farba;
        this.dlzka = dlzka;
        this.hlavna_suradnica = suradnica;
    }
    @Override
    public double obvod() {
        return 2*(dlzka+dlzka);
    }

    @Override
    public double obsah() {
        return dlzka*dlzka;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(farba);
        g.fillRect(hlavna_suradnica.getX(), hlavna_suradnica.getY(), dlzka, dlzka);
    }
}

public class Platno extends Panel{
    private ArrayList<GeomUtvar> shapes = new ArrayList<GeomUtvar>();

    public void addUtvar(GeomUtvar utvar){
        shapes.add(utvar);
    }
    public void removeUtvar(GeomUtvar utvar){
        shapes.remove(utvar);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for (GeomUtvar geomUtvar : shapes){
            geomUtvar.draw(g2);
        }
    }
}
