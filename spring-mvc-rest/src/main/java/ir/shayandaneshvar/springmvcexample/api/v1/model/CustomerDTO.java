package ir.shayandaneshvar.springmvcexample.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @ApiModelProperty(value = "This the first name",required = true)
    private String firstName;
    private String lastName;
    @JsonProperty("customer_url")
    private String customerUrl;
}
