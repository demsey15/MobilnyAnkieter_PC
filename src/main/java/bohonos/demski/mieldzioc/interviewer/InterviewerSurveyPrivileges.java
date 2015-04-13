/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.interviewer;

/**
 *
 * @author Delirus
 */
public class InterviewerSurveyPrivileges {
    public boolean editing, filling, editingWithoutAdminAgreement, fillingStatistics;
    public void setEditing(boolean editing){
        this.editing= editing;
    }
    public void setFilling(boolean filling){
        this.filling= filling;
    }
    public void setEditingWithoutAdminAgreement(boolean editingWithoutAdminAgreement){
        this.editingWithoutAdminAgreement= editingWithoutAdminAgreement;
    }
    public void setFillingStatistics(boolean fillingStatistics){
        this.fillingStatistics= fillingStatistics;
    }
}
