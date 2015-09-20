package com.enginemobi.core.catalog.domain;

import com.enginemobi.common.money.Money;
import com.enginemobi.core.generic.domain.BmSuiteEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementations of this interface are used to hold data for a Product.  A product is a general description
 * of an item that can be sold (for example: a hat).
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Product is persisted.  If you just want to add additional fields then you should extend {@link ProductImpl}.
 *
 * @author bc
 * @see {@link ProductImpl}
 */
interface Product extends BmSuiteEntity<Long, Product> {

    /**
     * Returns the name of the product that is used for display purposes.
     * <br />
     * <br />
     *
     * @return the name of the product
     */
    String getName();

    /**
     * Sets the name of the product that is used for display purposes.
     * <br />
     * <br />
     *
     * @param name - the name of the Product
     */
    void setName(String name);

    /**
     * Returns a brief description of the product that is used for display.
     * <br />
     * <br />
     *
     * @return a brief description of the product
     */
    String getDescription();

    /**
     * Sets a brief description of the product that is used for display.
     * <br />
     * <br />
     *
     * @param description - a brief description of the product
     */
    void setDescription(String description);

    /**
     * Returns a long description of the product that is used for display.
     * <br />
     * <br />
     *
     * @return a long description of the product
     */
    String getLongDescription();

    /**
     * Sets a long description of the product that is used for display.
     * <br />
     * <br />
     *
     * @param longDescription the long description
     */
    void setLongDescription(String longDescription);

    /**
     * Return whether or not the product is virtual
     * @return
     */
    boolean getProductIsVirtual();

    /**
     * Sets whether or not the product is virtual
     * @param isVirtual
     */
    void setProductIsVirtual(boolean isVirtual);

    /**
     * Return whether or not the product is shippable
     * @return
     */
    boolean getProductIsShippable();

    /**
     * Sets whether or not the product is shippable
     */
    void setProductIsShippable(boolean isShippable);

    /**
     * Return whether or not the product is free
     * @return
     */
    boolean getProductIsFree();

    /**
     * Sets whether or not the product is free
     */
    void setProductIsFree(boolean productIsFree);

    /**
     * Returns the first date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     *
     * @return the first date the product will be available
     */
    Date getActiveStartDate();

    /**
     * Sets the first date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     *
     * @param activeStartDate - the first day the product is available
     */
    void setActiveStartDate(Date activeStartDate);

    /**
     * Returns the last date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     *
     * @return the last day the product is available
     */
    Date getActiveEndDate();

    /**
     * Sets the last date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     *
     * @param activeEndDate - the last day the product is available
     */
    void setActiveEndDate(Date activeEndDate);

    /**
     * Returns a boolean that indicates if the product is currently active.
     * <br />
     * <br />
     *
     * @return a boolean indicates if the product is active.
     */
    boolean isActive();

    /**
     * Gets the Sku associated with this Product. A Product is
     * required to have a Sku which is used as unique product code.
     *
     * @return the sku associated with this product
     */
    String getSku();

    /**
     * Sets the Sku for this Product
     *
     * @param sku
     */
    void setSku(String sku);

    /**
     * Return the {@link Category} that contains this product
     *
     * @return
     */
    Category getCategory();

    /**
     * Set the {@link Category} that contains this product
     *
     * @param category
     */
    void setCategory(Category category);


    /**
     * Gets the product availability info
     * @return
     */
    ProductAvailability getProductAvailability();


    /**
     * Sets the product availability
     * @param productAvailability
     */
    void setProductAvailability(ProductAvailability productAvailability);

    /**
     * Returns the {@link Dimension} for this product
     * <br />
     * <br />
     *
     * @return a ProductDimensions object
     */
    Dimension getDimension();

    /**
     * Sets the {@link Dimension} for this product
     * <br />
     * <br />
     *
     * @param dimension
     */
    void setDimension(Dimension dimension);

    /**
     * Returns the dimension width
     * <br />
     * <br />
     *
     * @return width dimension of the product
     */
    BigDecimal getWidth();

    /**
     * Sets the dimension width
     * <br />
     * <br />
     *
     * @param width
     */
    void setWidth(BigDecimal width);

    /**
     * Returns the dimension height
     * <br />
     * <br />
     *
     * @return height dimension of the product
     */
    BigDecimal getHeight();

    /**
     * Sets the dimension height
     * <br />
     * <br />
     *
     * @param height
     */
    void setHeight(BigDecimal height);

    /**
     * Returns the dimension depth
     * <br />
     * <br />
     *
     * @return width depth of the product
     */
    BigDecimal getDepth();

    /**
     * Sets the dimension depth
     * <br />
     * <br />
     *
     * @param depth
     */
    void setDepth(BigDecimal depth);

    /**
     * Gets the dimension girth
     * <br />
     * <br />
     *
     * @return the dimension girth
     */
    BigDecimal getGirth();

    /**
     * Sets the dimension girth
     * <br />
     * <br />
     *
     * @param girth
     */
    void setGirth(BigDecimal girth);


    /**
     * Returns a String representation of the dimension
     * <br />
     * <br />
     *
     * @return a dimension String
     */
    String getDimensionString();

    /**
     * Returns the weight of the product
     * <br />
     * <br />
     *
     * @return weight of product
     */
    Weight getWeight();

    /**
     * Sets the product weight
     * <br />
     * <br />
     *
     * @param weight
     */
    void setWeight(Weight weight);

    /**
     * Returns a List of this product's related Cross Sales
     *
     * @return
     */
    List<RelatedProduct> getCrossSaleProducts();

    /**
     * Sets the related Cross Sales
     *
     * @param crossSaleProducts
     */
    void setCrossSaleProducts(List<RelatedProduct> crossSaleProducts);

    /**
     * Returns a List of this product's related Up Sales
     *
     * @return
     */
    List<RelatedProduct> getUpSaleProducts();

    /**
     * Sets the related Up Sales
     *
     * @param upSaleProducts
     */
    void setUpSaleProducts(List<RelatedProduct> upSaleProducts);

    /**
     * Returns whether or not the product is featured
     *
     * @return isFeaturedProduct as Boolean
     */
    boolean isFeaturedProduct();

    /**
     * Sets whether or not the product is featured
     *
     * @param isFeaturedProduct
     */
    void setFeaturedProduct(boolean isFeaturedProduct);


    /**
     * Gets the promotional message for this Product. For instance, this could be a limited-time
     * Product
     *
     * @return the Product's promotional message
     */
    String getPromoMessage();

    /**
     * Sets the promotional message for this Product
     *
     * @param promoMessage
     */
    void setPromoMessage(String promoMessage);


    /**
     * The available {@link ProductOption}s for this Product.  For instance, if this
     * Product is a T-Shirt, you might be able to specify a size and color. This would
     * be modeled by 2 {@link ProductOption}s, each that could have multiple {@link ProductOptionValue}s
     * (which could be "small" "medium" "large", "blue", "yellow", "green").  For specific pricing or
     * inventory needs on a per-value basis, multiple Skus can be associated to this Product based
     * off of the {@link ProductOptionValue}s
     *
     * @return the {@link ProductOption}s for this Product
     * @see {@link ProductOption}, {@link ProductOptionValue}
     */
    List<ProductOption> getProductOptions();

    /**
     * Sets the list of available ProductOptions for this Product
     *
     * @param productOptions
     */
    void setProductOptions(List<ProductOption> productOptions);


    /**
     * Returns a Map of product option values, keyed by the product option name.
     * E.g. "color":["red","green","black"]
     *
     * @return
     */
    Map<String, Set<String>> getProductOptionValuesMap();


    /**
     * A product can have a designated URL.   When set, the ProductHandlerMapping will check for this
     * URL and forward this user to the {@link #getDisplayTemplate()}.
     * <p/>
     * Alternatively, most sites will rely on the {@link Product#getGeneratedUrl()} to define the
     * url for a product page.
     *
     * @return
     */
    String getUrl();

    /**
     * Sets the URL that a customer could type in to reach this product.
     *
     * @param url
     */
    void setUrl(String url);

    /**
     * @return the flag for whether or not the URL should not be generated in the admin
     */
    Boolean getOverrideGeneratedUrl();

    /**
     * Sets the flag for whether or not the URL should not be generated in the admin
     *
     * @param overrideGeneratedUrl
     */
    void setOverrideGeneratedUrl(Boolean overrideGeneratedUrl);

    /**
     * Sets a url-fragment.  By default, the system will attempt to create a unique url-fragment for
     * this product by taking the {@link this.getName()} and removing special characters and replacing
     * dashes with spaces.
     */
    String getUrlKey();

    /**
     * Sets a url-fragment to be used with this product.  By default, the system will attempt to create a
     * unique url-fragment for this product by taking the {@link this.getName()} and removing special characters and replacing
     * dashes with spaces.
     */
    void setUrlKey(String urlKey);

    /**
     * Returns the name of a display template that is used to render this product.   Most implementations have a default
     * template for all products.    This allows for the user to define a specific template to be used by this product.
     *
     * @return
     */
    String getDisplayTemplate();

    /**
     * Sets the name of a display template that is used to render this product.   Most implementations have a default
     * template for all products.    This allows for the user to define a specific template to be used by this product.
     *
     * @param displayTemplate
     */
    void setDisplayTemplate(String displayTemplate);

    /**
     * Generates a URL that can be used to access the product.
     * Builds the url by combining the url of the default category with the getUrlKey() of this product.
     */
    String getGeneratedUrl();


    /**
     * Returns all parent {@link Category}(s) this product is associated with.
     *
     * @return the all parent categories for this product
     */
    List<Category> getAllParentCategories();

    /**
     * Sets all parent {@link Category}s this product is associated with.
     *
     * @param allParentCategories - a List of all parent {@link Category}(s) to associate this product with
     */
    void setAllParentCategories(List<Category> allParentCategories);


    /**
     * Returns the tax code of the product. If the tax code is null, then returns the tax code of this products category.
     *
     * @return taxCode
     */
    String getTaxCode();

    /**
     * Sets the tax code for this product.
     *
     * @param taxCode
     */
    void setTaxCode(String taxCode);

    /**
     * added just for convenience, references to defaultSku.retailPrice
     *
     * @return
     */
    Money getRetailPrice();

    /**
     * added just for convenience, references defaultSku.retailPrice
     *
     * @return
     */
    Money getSalePrice();


}
