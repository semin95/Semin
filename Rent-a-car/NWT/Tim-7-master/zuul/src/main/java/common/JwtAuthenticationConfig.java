package common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;


/**
 * Config JWT.
 */
@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${security.jwt.url:/login}")
    private String url;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:RentaCar}")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${security.jwt.secret}")
    private String secret;

	public String getUrl() {
		
		return url;
	}

	public String getHeader() {

		return header;
	}

	public String getPrefix() {

		return prefix;
	}

	public String getSecret() {

		return secret;
	}

	public long getExpiration() {
		
		return expiration;
	}


}
