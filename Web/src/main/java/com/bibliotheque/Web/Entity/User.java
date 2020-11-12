package com.bibliotheque.Web.Entity;

import lombok.Data;

@Data
public class User {

        private int id;
        public String name ;
        public String email;
        public String password;
        private String token;

 }
