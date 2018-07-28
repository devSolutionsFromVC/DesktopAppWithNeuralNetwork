package com.solution.fromVC.service;

import com.solution.fromVC.dao.AssetDAO;
import com.solution.fromVC.dao.AssetDAOImp;
import com.solution.fromVC.model.Assets;

import java.util.List;

public class AssetServiceImp implements AssetI{

    private AssetDAO assetDAO;

    public AssetServiceImp() {
        assetDAO = new AssetDAOImp();
    }

    @Override
    public void addNewAsset(Assets asset) {
        assetDAO.addNewAsset(asset);
    }

    @Override
    public void deleteAsset(Long id) {
        assetDAO.deleteAsset(id);
    }

    @Override
    public void updateAsset(Assets asset) {
        assetDAO.updateAsset(asset);
    }

    @Override
    public void removeAsset(List<Assets> asset) {
        assetDAO.removeAsset(asset);
    }

    @Override
    public Assets findAsset(Long id) {
        return assetDAO.findAsset(id);
    }

    @Override
    public Assets findAssetByNameAndDescription(String name, String description) {
        return assetDAO.findAssetByNameAndDescription(name, description);
    }

    @Override
    public List<Assets> getAsset() {
        return assetDAO.getAsset();
    }
}
