
package eu.verdelhan.ecoledirecte.v3.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GtkCookies {
    private final String gtkCookieString;
    private final String secondCookieString;
}
