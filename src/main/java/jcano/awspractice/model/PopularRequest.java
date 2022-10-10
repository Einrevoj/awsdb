package jcano.awspractice.model;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class PopularRequest {

    private String productName;
    private float price;

}
