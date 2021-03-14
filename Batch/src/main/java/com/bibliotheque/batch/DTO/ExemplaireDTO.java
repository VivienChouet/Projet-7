package com.bibliotheque.batch.DTO;

import lombok.Data;

    @Data
    public class ExemplaireDTO {

        public int id;
        public String edition;
        public boolean available;


        private BookDTO book;

    }


