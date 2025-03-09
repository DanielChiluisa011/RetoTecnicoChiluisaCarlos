package com.retotecnico.proyecto.application.util;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class Shared {
    public static String sanitizeString(String value){
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        String safeValue= policy.sanitize( value );
        return safeValue;
    }
}
