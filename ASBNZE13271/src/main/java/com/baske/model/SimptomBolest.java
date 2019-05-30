package com.baske.model;
import org.kie.api.definition.type.Position;

@Deprecated
public class SimptomBolest {

    @Position(0)
    private Simptomi simptom;

    @Position(1)
    private Bolest bolest;

	public SimptomBolest(Simptomi simptom, Bolest bolest) {
		super();
		this.simptom = simptom;
		this.bolest = bolest;
	}

	public Simptomi getSimptom() {
		return simptom;
	}

	public void setSimptom(Simptomi simptom) {
		this.simptom = simptom;
	}

	public Bolest getBolest() {
		return bolest;
	}

	public void setBolest(Bolest bolest) {
		this.bolest = bolest;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        SimptomBolest sss = (SimptomBolest) o;

        if (simptom != null ? !simptom.equals(sss.simptom) : sss.simptom != null) { return false; }
        if (bolest != null ? !bolest.equals(sss.bolest) : sss.bolest != null) { return false; }

        return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	

}
