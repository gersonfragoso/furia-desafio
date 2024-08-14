package org.example;

public class Player {
    private String name;  // Nome do Jogador.
    private int maps;  // Numero de mapas jogados.
    private int rounds; // Numero de flash lançados no periodo de tempo de 12 meses.
    private double thrown;  // Media de flash por round.
    private String blinded;  // Media de tempo em que o Jogador ficou cego por round.
    private String oppFlashed; // Media de tempo em que o Jogador deixou o adversario cego por round.
    private double diff;  // Media de tempo de (Oponentes cego - Vezes cegadas).
    private double fa;  // Assistencia por flash (quando o oponente morreu cego) por round.
    private double success;  // Media de sucesso ao fazer uma flash e cegar o oponente.
    public Player(String name, int maps, int rounds, double thrown, String blinded, String oppFlashed, double diff, double fa, double success) {
        this.name = name;
        this.maps = maps;
        this.rounds = rounds;
        this.thrown = thrown;
        this.blinded = blinded;
        this.oppFlashed = oppFlashed;
        this.diff = diff;
        this.fa = fa;
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public int getMaps() {
        return maps;
    }

    public int getRounds() {
        return rounds;
    }

    public double getThrown() {
        return thrown;
    }

    public String getBlinded() {
        return blinded;
    }

    public String getOppFlashed() {
        return oppFlashed;
    }

    public double getDiff() {
        return diff;
    }

    public double getFa() {
        return fa;
    }

    public double getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return
                " "+ name + '\n' +
                "Total de Mapas Jogados: " + maps + '\n' +
                "Total de Flashs: " + rounds+ '\n' +
                "Media Flashs por Round: " + thrown +'\n' +
                "Media de tempo cego por Round: " + blinded + '\n' +
                "Media de tempo de Oponente cego: " + oppFlashed + '\n' +
                "Tempo Blinded - Tempo de Oponente cego: " + diff + '\n' +
                "Assistencia com flash: " + fa  + '\n' +
                "Media de sucesso da flash por Round: " + success;
    }
}
