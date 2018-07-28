package com.solution.fromVC.NeuralService;


public class RiskInputData {

    private double likelihood;
    private double loss;
    private double assessment;

    private MLPerceptron perceptron = new MLPerceptron();

    public RiskInputData() {
    }

    public RiskInputData(double likelihood, double loss, double assessment) {
        this.likelihood = likelihood;
        this.loss = loss;
        this.assessment = assessment;
    }

    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public double getAssessment() {
        return assessment;
    }

    public void setAssessment(double assessment) {
        this.assessment = assessment;
    }

    public double[] riskAttributes(){
        double [] riskData = new double[3];
        riskData[0] = this.likelihood / 5;
        riskData[1] = this.loss / 5;
        riskData[2] = this.assessment / 5;
        return riskData;
    }

    @Override
    public String toString() {
        return "RiskInputData{" +
                "likelihood=" + likelihood +
                ", loss=" + loss +
                ", assessment=" + assessment +
                '}';
    }
}
