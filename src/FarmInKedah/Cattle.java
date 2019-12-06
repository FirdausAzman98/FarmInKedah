/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FarmInKedah;

/**
 *
 * @author User
 */
public class Cattle{
 private String Kg,type,date;
 
    final private String id;
    
    Cattle(String id,String Kg,String type,String date){
        this.id=id;
        this.Kg = Kg;
        this.type = type;
        this.date = date;
    }
    void setKg(String Kg){
        this.Kg = Kg;    
    }
    void settype(String type){
        this.type = type;
    }
    void setDate(String date){
        this.date = date;
    }
    String getid(){
        return id;
    }
    String getKg(){
        return Kg;
    }
    String gettype(){
        return type;
    }
    String getDate(){
        return date;
    }
}
