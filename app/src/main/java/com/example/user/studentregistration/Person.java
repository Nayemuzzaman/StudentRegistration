package com.example.user.studentregistration;

import java.io.Serializable;

/**
 * Created by Moudud on 7/22/2017.
 */

public class Person implements Serializable {
    private String _id;
    private String person_name;
    private String person_email;
    private String person_phone;
    private String person_address;

    public Person() {
    }

    public Person(String _id, String person_name, String person_email, String person_phone, String person_address) {
        this._id = _id;
        this.person_name = person_name;
        this.person_email= person_email;
        this.person_phone = person_phone;
        this.person_address = person_address;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_email() {
        return person_email;
    }

    public void setPerson_email(String person_email) {
        this.person_email = person_email;
    }

    public String getPerson_phone() {
        return person_phone;
    }

    public void setPerson_phone(String person_phone) {
        this.person_phone = person_phone;
    }

    public String getPerson_address() {
        return person_address;
    }

    public void setPerson_address(String person_address) {
        this.person_address = person_address;
    }
}
