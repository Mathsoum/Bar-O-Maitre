package bar.o.maitre

import grails.transaction.Transactional

@Transactional
class MemberService {
    MemberRankService memberRankService

    public void updateRank(Member member, int reputationAdded){
        member.reputation += reputationAdded
        /* Membre de base */
        if (member.reputation < 5) {
            memberRankService.attribute_user_role(member)
        } /* Adepte */
        else if (member.reputation < 10){
            memberRankService.attribute_adept_role(member)
        } /* Confirme */
        else if (member.reputation < 15){
            memberRankService.attribute_confirmed_role(member)
        }/* Expert */
        else if (member.reputation < 20){
            memberRankService.attribute_expert_role(member)
        } else {
            memberRankService.attribute_admin_role(member)
        }
    }
}
