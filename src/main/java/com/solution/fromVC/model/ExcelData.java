package com.solution.fromVC.model;

public class ExcelData {

    private String asset;
    private String confidentiality;
    private String integrity;
    private String accessibility;
    private String assetWorth;
    private String threat;
    private String vulnerability;
    private String likelihood;
    private String loss;
    private String value;
    private String riskResult;
    private String waysToReduce;

    public ExcelData() {
    }

    public ExcelData(String asset, String confidentiality,
                     String integrity, String accessibility,
                     String assetWorth, String threat,
                     String vulnerability, String likelihood,
                     String loss, String value, String riskResult,
                     String waysToReduce) {
        this.asset = asset;
        this.confidentiality = confidentiality;
        this.integrity = integrity;
        this.accessibility = accessibility;
        this.assetWorth = assetWorth;
        this.threat = threat;
        this.vulnerability = vulnerability;
        this.likelihood = likelihood;
        this.loss = loss;
        this.value = value;
        this.riskResult = riskResult;
        this.waysToReduce = waysToReduce;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(String confidentiality) {
        this.confidentiality = confidentiality;
    }

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getAssetWorth() {
        return assetWorth;
    }

    public void setAssetWorth(String assetWorth) {
        this.assetWorth = assetWorth;
    }

    public String getThreat() {
        return threat;
    }

    public void setThreat(String threat) {
        this.threat = threat;
    }

    public String getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }

    public String getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(String likelihood) {
        this.likelihood = likelihood;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRiskResult() {
        return riskResult;
    }

    public void setRiskResult(String riskResult) {
        this.riskResult = riskResult;
    }

    public String getWaysToReduce() {
        return waysToReduce;
    }

    public void setWaysToReduce(String waysToReduce) {
        this.waysToReduce = waysToReduce;
    }
}
