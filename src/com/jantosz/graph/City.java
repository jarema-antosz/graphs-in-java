package com.jantosz.graph;

/**
 * Created by jarema.antosz on 21.07.2016.
 */
public class City
{

	private String name;

	public City(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		City city = (City) o;

		return name.equals(city.name);

	}

	@Override
	public int hashCode()
	{
		return name.hashCode();
	}

	@Override
	public String toString()
	{
		return name;
	}
}
