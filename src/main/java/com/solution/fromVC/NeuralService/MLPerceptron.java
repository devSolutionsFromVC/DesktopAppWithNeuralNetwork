package com.solution.fromVC.NeuralService;


import com.solution.fromVC.fxconfig.ProcessingRiskController;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

import java.io.IOException;

public class MLPerceptron {

    private static String riskResult;

    private static double [] rid = new ProcessingRiskController().processingResult();

    public static void main(String[] args) {

        DataSet training = study();

        MultiLayerPerceptron neuralNet = initMLP();

        neuralNet.learn(training);

        findRiskSolution(neuralNet, training, rid);

    }

    private static MultiLayerPerceptron initMLP() {
        int IL = 3;
        int HL = 4;
        int OL = 1;

        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, IL, HL, OL);

        double learningRate = 0.5;
        double momentum = 0.8;

        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.setLearningRate(learningRate);
        learningRule.setMomentum(momentum);

        return neuralNet;
    }

    private static DataSet study(){
        DataSet trainingSet = null;

        String trainingSetFileName = "Traine.txt";
        int inputsCount = 3;
        int outputsCount = 1;

        try {
            trainingSet = TrainingSetImport.importFromFile(trainingSetFileName, inputsCount, outputsCount, ",");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        }
        return trainingSet;
    }

    private static void findRiskSolution(NeuralNetwork nNet, DataSet trainingSet, double [] rid) {
        DataSetRow set = new DataSetRow(rid);
//        for(DataSetRow trainingElement: trainingSet.getRows()){
//            nNet.setInput(trainingElement.getInput());
            nNet.setInput(set.getInput());
            nNet.calculate();
            double[] output = nNet.getOutput();

            riskResult = RiskSolution.riskResult(output);
//            System.out.println("Нужно принять решение: " + riskResult);
//        }
    }

    public static String riskResult(){
        DataSet training = study();

        MultiLayerPerceptron neuralNet = initMLP();

//        neuralNet.learn(training);

        findRiskSolution(neuralNet, training, rid);
        return riskResult;
    }
}
