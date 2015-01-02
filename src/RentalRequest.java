/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class RentalRequest {
    String _id;
    String _assetType;
    int _assetSize;
    int _durationOfStay;
    Asset _asset;
    String _requestStatus;

    public RentalRequest(String _id, String _assetType, int _assetSize, int _durationOfStay) {
        this._id = _id;
        this._assetType = _assetType;
        this._assetSize = _assetSize;
        this._durationOfStay = _durationOfStay;
        this._requestStatus ="INCOMPLETE";
    }
    
}
