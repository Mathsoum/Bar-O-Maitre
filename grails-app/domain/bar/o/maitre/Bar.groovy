package bar.o.maitre

import grails.plugin.springsecurity.acl.AclClass
import grails.plugin.springsecurity.acl.AclEntry
import grails.plugin.springsecurity.acl.AclObjectIdentity
import grails.plugin.springsecurity.acl.AclSid
import org.springframework.security.acls.domain.BasePermission

class Bar {

    String barName
    String description
    String address
    String type
    String price

    /*
    TODO: Faire le lien avec le domaine membre ( constraint, test, controller ...)
     */
    Member admin

    static constraints = {
        barName blank: false, nullable : false
        description blank: false, nullable : false
        address blank: false, nullable : false
        type blank: false, nullable : false
        price blank: false, nullable : false
        admin blank: false, nullable : false
    }

    def afterInsert() {
        AclSid currentUserSid = AclSid.findBySid(admin.username)

        AclObjectIdentity aclObjectIdentity = new AclObjectIdentity(
            aclClass: AclClass.findByClassName(Bar.class.getName()),
            objectId: id,
            owner : currentUserSid
        )
        aclObjectIdentity.save()

        AclEntry aclEntry = new AclEntry(
            aclObjectIdentity: aclObjectIdentity,
            sid: currentUserSid,
            mask: BasePermission.ADMINISTRATION.mask,
            aceOrder: 0,
            granting: false,
            auditSuccess: false,
            auditFailure: false
        )
        aclEntry.save()
    }


    @Override
    public String toString() {
        return barName;
    }
}
