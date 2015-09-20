package com.enginemobi.core.catalog.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;
import com.enginemobi.core.store.domain.Store;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

/**
 * Implementations of this interface are used to hold data about a Category.  A category is a group of products.
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Category is persisted.  If you just want to add additional fields then you should extend {@link CategoryImpl}.
 *
 * @see {@link CategoryImpl}
 */
public interface Category extends BmSuiteEntity<Long, Category> {

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Nonnull
     String getName();

    /**
     * Sets the name.
     *
     * @param name the new name
     */
     void setName(@Nonnull String name);

    /**
     * Gets whether the category is visible
     * @return
     */
    boolean isVisible();

    /**
     * Sets the category visible status
     * @param visible
     */
    void setVisible(boolean visible);

    Integer getDepth();

    void setDepth(Integer depth);


    Store getStore();

    void setStore(Store merchantStore);


    /**
     * Return the category that is the parent of this category - if applicable
     *
     * @return
     */
    Category getParentCategory();

    /**
     * Set the parent category of this category
     *
     * @param category
     */
    void setParentCategory(Category category);

    /**
     * Gets the url. The url represents the presentation layer destination for
     * this category. For example, if using Spring MVC, you could send the user
     * to this destination by returning {@code "redirect:"+currentCategory.getUrl();}
     * from a controller.
     *
     * @return the url for the presentation layer component for this category
     */
    @Nullable
     String getUrl();

    /**
     * Sets the url. The url represents the presentation layer destination for
     * this category. For example, if using Spring MVC, you could send the user
     * to this destination by returning {@code "redirect:"+currentCategory.getUrl();}
     * from a controller.
     *
     * @param url the new url for the presentation layer component for this category
     */
     void setUrl(@Nullable String url);

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Nullable
     String getDescription();

    /**
     * Sets the description.
     *
     * @param description the new description
     */
     void setDescription(@Nullable String description);

    /**
     * Gets the active start date. If the current date is before activeStartDate,
     * then this category will not be visible on the site.
     *
     * @return the active start date
     */
    @Nullable
     Date getActiveStartDate();

    /**
     * Sets the active start date. If the current date is before activeStartDate,
     * then this category will not be visible on the site.
     *
     * @param activeStartDate the new active start date
     */
     void setActiveStartDate(@Nullable Date activeStartDate);

    /**
     * Gets the active end date. If the current date is after activeEndDate,
     * the this category will not be visible on the site.
     *
     * @return the active end date
     */
    @Nullable
     Date getActiveEndDate();

    /**
     * Sets the active end date. If the current date is after activeEndDate,
     * the this category will not be visible on the site.
     *
     * @param activeEndDate the new active end date
     */
     void setActiveEndDate(@Nullable Date activeEndDate);

    /**
     * Checks if is active. Returns true if the startDate is null or if the current
     * date is after the start date, or if the endDate is null or if the current date
     * is before the endDate.
     *
     * @return true, if is active
     */
     boolean isActive();

    /**
     * Gets the display template. The display template can be used to help create a unique key
     * that drives the presentation layer destination for this category. For example, if
     * using Spring MVC, you might derive the view destination in this way:
     *
     * {@code view = categoryTemplatePrefix + currentCategory.getDisplayTemplate();}
     *
     * @return the display template
     */
    @Nullable
     String getDisplayTemplate();

    /**
     * Sets the display template. The display template can be used to help create a unique key
     * that drives the presentation layer destination for this category. For example, if
     * using Spring MVC, you might derive the view destination in this way:
     *
     * {@code view = categoryTemplatePrefix + currentCategory.getDisplayTemplate();}
     *
     * @param displayTemplate the new display template
     */
     void setDisplayTemplate(@Nullable String displayTemplate);


    /**
     * Gets the long description.
     *
     * @return the long description
     */
    @Nullable
     String getLongDescription();

    /**
     * Sets the long description.
     *
     * @param longDescription the new long description
     */
     void setLongDescription(@Nullable String longDescription);

    /**
     * Gets the featured products. Featured products are a special list
     * of products you would like to showcase for this category.
     *
     * @return the featured products
     */
    @Nonnull
     List<FeaturedProduct> getFeaturedProducts();

    /**
     * Sets the featured products. Featured products are a special list
     * of products you would like to showcase for this category.
     *
     * @param featuredProducts the featured products
     */
     void setFeaturedProducts(@Nonnull List<FeaturedProduct> featuredProducts);

    /**
     * Returns a list of cross sale products that are related to this category.
     *
     * @return a list of cross sale products
     */
     List<RelatedProduct> getCrossSaleProducts();

    /**
     * Sets the cross sale products that are related to this category.
     *
     * @see #getCrossSaleProducts()
     * @param crossSaleProducts
     */
     void setCrossSaleProducts(List<RelatedProduct> crossSaleProducts);

    /**
     * Returns a list of cross sale products that are related to this category.
     *
     * @return a list of cross sale products
     */
     List<RelatedProduct> getUpSaleProducts();

    /**
     * Sets the upsale products that are related to this category.
     *
     * @see #getUpSaleProducts()
     * @param upSaleProducts
     */
     void setUpSaleProducts(List<RelatedProduct> upSaleProducts);

    /**
     * Returns the tax code of this category.
     * @return taxCode
     */
     String getTaxCode();

    /**
     * Sets the tax code of this category.
     * @param taxCode
     */
     void setTaxCode(String taxCode);


}
