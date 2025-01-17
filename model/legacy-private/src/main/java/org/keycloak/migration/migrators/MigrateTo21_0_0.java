package org.keycloak.migration.migrators;

import org.keycloak.migration.ModelVersion;
import org.keycloak.models.AccountRoles;
import org.keycloak.models.ClientModel;
import org.keycloak.models.Constants;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.representations.idm.RealmRepresentation;

public class MigrateTo21_0_0 implements Migration {

    public static final ModelVersion VERSION = new ModelVersion("21.0.0");

    @Override
    public void migrate(KeycloakSession session) {
        session.realms().getRealmsStream().forEach(this::updateAdminTheme);
    }

    @Override
    public void migrateImport(KeycloakSession session, RealmModel realm, RealmRepresentation rep, boolean skipUserDependent) {
        updateAdminTheme(realm);
    }

    private void updateAdminTheme(RealmModel realm) {
        String adminTheme = realm.getAdminTheme();
        if (adminTheme.equals("keycloak") || adminTheme.equals("rh-sso")) {
            realm.setAdminTheme("keycloak.v2");
        }
    }

    @Override
    public ModelVersion getVersion() {
        return VERSION;
    }
}
