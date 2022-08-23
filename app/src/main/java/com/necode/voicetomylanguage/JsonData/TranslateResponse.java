package com.necode.voicetomylanguage.JsonData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TranslateResponse {
    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;
    @SerializedName("quotaFinished")
    @Expose
    private Boolean quotaFinished;
    @SerializedName("mtLangSupported")
    @Expose
    private Object mtLangSupported;
    @SerializedName("responseDetails")
    @Expose
    private String responseDetails;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("responderId")
    @Expose
    private String responderId;
    @SerializedName("exception_code")
    @Expose
    private Object exceptionCode;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public Boolean getQuotaFinished() {
        return quotaFinished;
    }

    public void setQuotaFinished(Boolean quotaFinished) {
        this.quotaFinished = quotaFinished;
    }

    public Object getMtLangSupported() {
        return mtLangSupported;
    }

    public void setMtLangSupported(Object mtLangSupported) {
        this.mtLangSupported = mtLangSupported;
    }

    public String getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    public Object getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Object exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}

class Match {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("segment")
    @Expose
    private String segment;
    @SerializedName("translation")
    @Expose
    private String translation;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("reference")
    @Expose
    private Object reference;
    @SerializedName("usage-count")
    @Expose
    private Integer usageCount;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("created-by")
    @Expose
    private String createdBy;
    @SerializedName("last-updated-by")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("create-date")
    @Expose
    private String createDate;
    @SerializedName("last-update-date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("match")
    @Expose
    private Double match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Double getMatch() {
        return match;
    }

    public void setMatch(Double match) {
        this.match = match;
    }

}

