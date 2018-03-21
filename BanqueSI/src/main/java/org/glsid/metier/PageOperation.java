package org.glsid.metier;

import java.io.Serializable;
import java.util.List;

import org.glsid.entities.Operation;

public class PageOperation implements Serializable {
private List<Operation> operations;

private int page;
private int nombreOperatios;
private int totalOperations;
private int totalPages;
public List<Operation> getOperations() {
	return operations;
}
public void setOperations(List<Operation> operations) {
	this.operations = operations;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getNombreOperatios() {
	return nombreOperatios;
}
public void setNombreOperatios(int nombreOperatios) {
	this.nombreOperatios = nombreOperatios;
}
public int getTotalOperations() {
	return totalOperations;
}
public void setTotalOperations(int totalOperations) {
	this.totalOperations = totalOperations;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}

}
