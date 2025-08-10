package eu.verdelhan.ecoledirecte;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class EcoleDirecteApiConfig {

    private final String baseUrl;

    private String versionSiteEcoleDirecte = "4.81.0";
}
