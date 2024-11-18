package model;

public class Patient {
    private int id;
    private double weight;
    private double height;

    public Patient(int id, double weight, double height) {
        this.id = id;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "\nID: " + id 
        		+ "\nPeso: " + weight + "kg" 
        		+ "\nAltura: " + height;
    }

    public String toFileString() {
        return id + ";" + weight + ";" + height;
    }

    public static Patient fromFileString(String line) {
        String[] data = line.split(";");
        if (data.length != 3) {
            throw new IllegalArgumentException("Linha de dados inválida para criar o Paciente: " + line);
        }

        try {
            int id = Integer.parseInt(data[0]);
            double weight = Double.parseDouble(data[1]);
            double height = Double.parseDouble(data[2]);
            return new Patient(id, weight, height);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de número inválido em: " + line, e);
        }
    }
}
