package com.solution.fromVC.service;

import com.solution.fromVC.model.Assets;

import java.util.List;

public interface AssetI {

    void addNewAsset(Assets asset);
    void deleteAsset(Long id);
    void updateAsset(Assets asset);
    void removeAsset(List<Assets> asset);
    Assets findAsset(Long id);
    Assets findAssetByNameAndDescription(String name, String description);
    List<Assets> getAsset();
}
