
package org.jobs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Job
 * 
 */
@Entity
public class Job
implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	@Id
	private Integer				id;

	private String				title;

	private String				company;

	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "FK_COUNTRY" )
	private Country				country;

	private String				locality;

	private String				description;

	private Date				posted;

	private Date				valability;

	@Enumerated( EnumType.STRING )
	private TimeWork			contractType;

	// junior, mid-senior level, management
	@Enumerated( EnumType.STRING )
	private ExperienceLevel		experienceLevel;

	private String				industry;

	private String				referalJobCode;

	private String				companyJobCode;

	private String				source;

	private String				referal;

	private String				advertiseUrl;

	private String				companyJobUrl;

	private Integer				salaryMin;

	private Integer				salaryMax;

	private Currency			salaryCurrency;

	private SalaryRange			salaryRange;

	private String				benefits;

	private String				contactName;

	private String				contactEmail;

	private String				contactCompany;

	private String				contactPhone;

	private JobState			state;

	public Job()
	{
		super();
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getCompany()
	{
		return this.company;
	}

	public void setCompany( String company )
	{
		this.company = company;
	}

	public Country getCountry()
	{
		return this.country;
	}

	public void setCountry( Country country )
	{
		this.country = country;
	}

	public String getLocality()
	{
		return this.locality;
	}

	public void setLocality( String locality )
	{
		this.locality = locality;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Date getPosted()
	{
		return this.posted;
	}

	public void setPosted( Date posted )
	{
		this.posted = posted;
	}

	public Date getValability()
	{
		return this.valability;
	}

	public void setValability( Date valability )
	{
		this.valability = valability;
	}

	public TimeWork getType()
	{
		return this.contractType;
	}

	public void setType( TimeWork type )
	{
		this.contractType = type;
	}

	public ExperienceLevel getExperienceLevel()
	{
		return this.experienceLevel;
	}

	public void setExperienceLevel( ExperienceLevel experienceLevel )
	{
		this.experienceLevel = experienceLevel;
	}

	public String getIndustry()
	{
		return this.industry;
	}

	public void setIndustry( String industry )
	{
		this.industry = industry;
	}

	public String getExternalId()
	{
		return this.referalJobCode;
	}

	public void setExternalId( String externalId )
	{
		this.referalJobCode = externalId;
	}

	public String getSource()
	{
		return this.source;
	}

	public void setSource( String source )
	{
		this.source = source;
	}

	public String getReferal()
	{
		return this.referal;
	}

	public void setReferal( String referal )
	{
		this.referal = referal;
	}

	public String getAdvertiseUrl()
	{
		return advertiseUrl;
	}

	public void setAdvertiseUrl( String advertiseUrl )
	{
		this.advertiseUrl = advertiseUrl;
	}

	public String getCompanyJobUrl()
	{
		return companyJobUrl;
	}

	public void setCompanyJobUrl( String companyJobUrl )
	{
		this.companyJobUrl = companyJobUrl;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getReferalJobCode()
	{
		return referalJobCode;
	}

	public void setReferalJobCode( String referalJobCode )
	{
		this.referalJobCode = referalJobCode;
	}

	public String getCompanyJobCode()
	{
		return companyJobCode;
	}

	public void setCompanyJobCode( String companyJobCode )
	{
		this.companyJobCode = companyJobCode;
	}

	public Integer getSalaryMin()
	{
		return salaryMin;
	}

	public void setSalaryMin( Integer salaryMin )
	{
		this.salaryMin = salaryMin;
	}

	public Integer getSalaryMax()
	{
		return salaryMax;
	}

	public void setSalaryMax( Integer salaryMax )
	{
		this.salaryMax = salaryMax;
	}

	public SalaryRange getSalaryRange()
	{
		return salaryRange;
	}

	public void setSalaryRange( SalaryRange salaryRange )
	{
		this.salaryRange = salaryRange;
	}

	public String getBenefits()
	{
		return benefits;
	}

	public void setBenefits( String benefits )
	{
		this.benefits = benefits;
	}

	public String getContactName()
	{
		return contactName;
	}

	public void setContactName( String contactName )
	{
		this.contactName = contactName;
	}

	public String getContactEmail()
	{
		return contactEmail;
	}

	public void setContactEmail( String contactEmail )
	{
		this.contactEmail = contactEmail;
	}

	public String getContactCompany()
	{
		return contactCompany;
	}

	public void setContactCompany( String contactCompany )
	{
		this.contactCompany = contactCompany;
	}

	public String getContactPhone()
	{
		return contactPhone;
	}

	public void setContactPhone( String contactPhone )
	{
		this.contactPhone = contactPhone;
	}

	public JobState getState()
	{
		return state;
	}

	public void setState( JobState state )
	{
		this.state = state;
	}

	public TimeWork getContractType()
	{
		return contractType;
	}

	public void setContractType( TimeWork contractType )
	{
		this.contractType = contractType;
	}

	public Currency getSalaryCurrency()
	{
		return salaryCurrency;
	}

	public void setSalaryCurrency( Currency salaryCurrency )
	{
		this.salaryCurrency = salaryCurrency;
	}

	@Override
	public boolean equals( Object obj )
	{
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		Job other = (Job) obj;
		if( id == null ) {
			if( other.id != null )
				return false;
		}
		else if( !id.equals( other.id ) )
			return false;
		return true;
	}

}
