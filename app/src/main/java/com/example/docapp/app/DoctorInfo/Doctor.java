package com.example.docapp.app.DoctorInfo;
import com.parse.ParseObject;
import com.parse.ParseClassName;
/**
 * Created by faisal on 23/5/14.
 */
@ParseClassName("Doctor")
public class Doctor  extends ParseObject{
    public Doctor(){

    }

    public String getFname(){
        return getString("Fname");

    }

    public String getLname(){
        return getString("Lname");

    }

    public String getDoctorId(){
        return getString("doctor_id");
    }



}
