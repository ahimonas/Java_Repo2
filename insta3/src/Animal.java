import java.io.Serializable;

public class Animal implements Serializable {
	private String name_of_ani;
	private String breed_of_ani;
	private double age_of_ani;
	private int total_num_ani;

	public Animal(String animalName, String animalBreed, double animalAge,
			int numInZoo) {
		super();
		this.name_of_ani = animalName;
		this.breed_of_ani = animalBreed;
		this.age_of_ani = animalAge;
		this.total_num_ani = numInZoo;
	}

	@Override
	public String toString() {
		return "Animal [animalName=" + name_of_ani + ", animalBreed="
				+ breed_of_ani + ", animalAge=" + age_of_ani + ", numInZoo="
				+ total_num_ani + "]";
	}

	public Animal() {
		super();
	}

	@Override
	public int hashCode() {
		final int curr_num_prime = 31;
		int my_curr_result = 1;
		my_curr_result = curr_num_prime * my_curr_result
				+ ((breed_of_ani == null) ? 0 : breed_of_ani.hashCode());
		my_curr_result = curr_num_prime * my_curr_result
				+ ((name_of_ani == null) ? 0 : name_of_ani.hashCode());
		return my_curr_result;
	}

	@Override
	public boolean equals(Object passedObj) {
		if (this == passedObj)
			return true;
		if (passedObj == null)
			return false;
		if (getClass() != passedObj.getClass())
			return false;
		Animal other = (Animal) passedObj;
		if (breed_of_ani == null) {
			if (other.breed_of_ani != null)
				return false;
		} else if (!breed_of_ani.equals(other.breed_of_ani))
			return false;
		if (name_of_ani == null) {
			if (other.name_of_ani != null)
				return false;
		} else if (!name_of_ani.equals(other.name_of_ani))
			return false;
		return true;
	}

	public String getAnimalName() {
		return name_of_ani;
	}

	public void setAnimalName(String animalName) {
		this.name_of_ani = animalName;
	}

	public String getAnimalBreed() {
		return breed_of_ani;
	}

	public void setAnimalBreed(String animalBreed) {
		this.breed_of_ani = animalBreed;
	}

	public double getAnimalAge() {
		return age_of_ani;
	}

	public void setAnimalAge(double animalAge) {
		this.age_of_ani = animalAge;
	}

	public int getNumInZoo() {
		return total_num_ani;
	}

	public void setNumInZoo(int numInZoo) {
		this.total_num_ani = numInZoo;
	}

}
