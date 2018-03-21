package org.glsid.metier;

import java.util.Date;

import javax.transaction.Transactional;

import org.glsid.dao.CompteRepository;
import org.glsid.dao.EmployeRepository;
import org.glsid.dao.OperationRepository;
import org.glsid.entities.Compte;
import org.glsid.entities.Employe;
import org.glsid.entities.Operation;
import org.glsid.entities.Retrait;
import org.glsid.entities.Versment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OperationMetierImpl  implements OperationMetier {
	@Autowired
    private OperationRepository operationRepository;
	@Autowired
    private CompteRepository compteRepository;
	@Autowired
    private EmployeRepository employeRepository;
    
	@Override
	@org.springframework.transaction.annotation.Transactional
	public boolean verser(String code, double montant, Long codeEmp) {
		Compte cp=compteRepository.findOne(code);
		Employe e=employeRepository.findOne(codeEmp);
		Operation o =new Versment();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		o.setEmploye(e);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()+montant);
		return true;
	}
	@org.springframework.transaction.annotation.Transactional
	@Override
	public boolean retirer(String code, double montant, Long codeEmp) {
		Compte cp=compteRepository.findOne(code);
		if(cp.getSolde()< montant)throw new RuntimeException("Solde insuffisant");
		Employe e=employeRepository.findOne(codeEmp);
		Operation o =new Retrait();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		o.setEmploye(e);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()-montant);
		return true;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public boolean virement(String cpte1, String cpte2, double montant, Long codeEmp) {
		retirer(cpte1,montant,codeEmp);
		verser(cpte2,montant,codeEmp);
		
		return true;
	}
	@Override
	public PageOperation getoperations(String codeCompte, int page, int size) {
		Page<Operation> ops=operationRepository.getOperations(codeCompte, new PageRequest(page,size));
		PageOperation pOp =new PageOperation();
		pOp.setOperations(ops.getContent());
		pOp.setNombreOperatios(ops.getNumberOfElements());
		pOp.setPage(ops.getNumber());
		pOp.setTotalPages(ops.getTotalPages());
		pOp.setTotalOperations((int)ops.getTotalElements());
		
		return pOp;
	} 

}
