package com.dam.salesianostriana.psp.ejemplo01_retrofitfirstsample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;

public class GitHub {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("private")
    @Expose
    private boolean _private;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("fork")
    @Expose
    private boolean fork;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("forks_url")
    @Expose
    private String forksUrl;
    @SerializedName("keys_url")
    @Expose
    private String keysUrl;
    @SerializedName("collaborators_url")
    @Expose
    private String collaboratorsUrl;
    @SerializedName("teams_url")
    @Expose
    private String teamsUrl;
    @SerializedName("hooks_url")
    @Expose
    private String hooksUrl;
    @SerializedName("issue_events_url")
    @Expose
    private String issueEventsUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("assignees_url")
    @Expose
    private String assigneesUrl;
    @SerializedName("branches_url")
    @Expose
    private String branchesUrl;
    @SerializedName("tags_url")
    @Expose
    private String tagsUrl;
    @SerializedName("blobs_url")
    @Expose
    private String blobsUrl;
    @SerializedName("git_tags_url")
    @Expose
    private String gitTagsUrl;
    @SerializedName("git_refs_url")
    @Expose
    private String gitRefsUrl;
    @SerializedName("trees_url")
    @Expose
    private String treesUrl;
    @SerializedName("statuses_url")
    @Expose
    private String statusesUrl;
    @SerializedName("languages_url")
    @Expose
    private String languagesUrl;
    @SerializedName("stargazers_url")
    @Expose
    private String stargazersUrl;
    @SerializedName("contributors_url")
    @Expose
    private String contributorsUrl;
    @SerializedName("subscribers_url")
    @Expose
    private String subscribersUrl;
    @SerializedName("subscription_url")
    @Expose
    private String subscriptionUrl;
    @SerializedName("commits_url")
    @Expose
    private String commitsUrl;
    @SerializedName("git_commits_url")
    @Expose
    private String gitCommitsUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("issue_comment_url")
    @Expose
    private String issueCommentUrl;
    @SerializedName("contents_url")
    @Expose
    private String contentsUrl;
    @SerializedName("compare_url")
    @Expose
    private String compareUrl;
    @SerializedName("merges_url")
    @Expose
    private String mergesUrl;
    @SerializedName("archive_url")
    @Expose
    private String archiveUrl;
    @SerializedName("downloads_url")
    @Expose
    private String downloadsUrl;
    @SerializedName("issues_url")
    @Expose
    private String issuesUrl;
    @SerializedName("pulls_url")
    @Expose
    private String pullsUrl;
    @SerializedName("milestones_url")
    @Expose
    private String milestonesUrl;
    @SerializedName("notifications_url")
    @Expose
    private String notificationsUrl;
    @SerializedName("labels_url")
    @Expose
    private String labelsUrl;
    @SerializedName("releases_url")
    @Expose
    private String releasesUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("pushed_at")
    @Expose
    private String pushedAt;
    @SerializedName("git_url")
    @Expose
    private String gitUrl;
    @SerializedName("ssh_url")
    @Expose
    private String sshUrl;
    @SerializedName("clone_url")
    @Expose
    private String cloneUrl;
    @SerializedName("svn_url")
    @Expose
    private String svnUrl;
    @SerializedName("homepage")
    @Expose
    private Object homepage;
    @SerializedName("size")
    @Expose
    private long size;
    @SerializedName("stargazers_count")
    @Expose
    private long stargazersCount;
    @SerializedName("watchers_count")
    @Expose
    private long watchersCount;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("has_issues")
    @Expose
    private boolean hasIssues;
    @SerializedName("has_downloads")
    @Expose
    private boolean hasDownloads;
    @SerializedName("has_wiki")
    @Expose
    private boolean hasWiki;
    @SerializedName("has_pages")
    @Expose
    private boolean hasPages;
    @SerializedName("forks_count")
    @Expose
    private long forksCount;
    @SerializedName("mirror_url")
    @Expose
    private Object mirrorUrl;
    @SerializedName("open_issues_count")
    @Expose
    private long openIssuesCount;
    @SerializedName("forks")
    @Expose
    private long forks;
    @SerializedName("open_issues")
    @Expose
    private long openIssues;
    @SerializedName("watchers")
    @Expose
    private long watchers;
    @SerializedName("default_branch")
    @Expose
    private String defaultBranch;

    /**
     * No args constructor for use in serialization
     *
     */
    public GitHub() {
    }

    /**
     *
     * @param notificationsUrl
     * @param releasesUrl
     * @param pushedAt
     * @param gitTagsUrl
     * @param contentsUrl
     * @param blobsUrl
     * @param issueEventsUrl
     * @param htmlUrl
     * @param _private
     * @param hooksUrl
     * @param description
     * @param commentsUrl
     * @param commitsUrl
     * @param labelsUrl
     * @param assigneesUrl
     * @param mergesUrl
     * @param fork
     * @param compareUrl
     * @param stargazersUrl
     * @param gitRefsUrl
     * @param watchersCount
     * @param openIssuesCount
     * @param mirrorUrl
     * @param homepage
     * @param url
     * @param size
     * @param keysUrl
     * @param gitCommitsUrl
     * @param milestonesUrl
     * @param downloadsUrl
     * @param issueCommentUrl
     * @param pullsUrl
     * @param owner
     * @param forksUrl
     * @param language
     * @param statusesUrl
     * @param eventsUrl
     * @param openIssues
     * @param teamsUrl
     * @param sshUrl
     * @param contributorsUrl
     * @param stargazersCount
     * @param tagsUrl
     * @param id
     * @param hasIssues
     * @param createdAt
     * @param name
     * @param treesUrl
     * @param cloneUrl
     * @param issuesUrl
     * @param gitUrl
     * @param forksCount
     * @param watchers
     * @param subscriptionUrl
     * @param svnUrl
     * @param archiveUrl
     * @param hasPages
     * @param languagesUrl
     * @param updatedAt
     * @param collaboratorsUrl
     * @param forks
     * @param hasDownloads
     * @param subscribersUrl
     * @param branchesUrl
     * @param fullName
     * @param hasWiki
     * @param defaultBranch
     */
    public GitHub(long id, String name, String fullName, Owner owner, boolean _private, String htmlUrl, String description, boolean fork, String url, String forksUrl, String keysUrl, String collaboratorsUrl, String teamsUrl, String hooksUrl, String issueEventsUrl, String eventsUrl, String assigneesUrl, String branchesUrl, String tagsUrl, String blobsUrl, String gitTagsUrl, String gitRefsUrl, String treesUrl, String statusesUrl, String languagesUrl, String stargazersUrl, String contributorsUrl, String subscribersUrl, String subscriptionUrl, String commitsUrl, String gitCommitsUrl, String commentsUrl, String issueCommentUrl, String contentsUrl, String compareUrl, String mergesUrl, String archiveUrl, String downloadsUrl, String issuesUrl, String pullsUrl, String milestonesUrl, String notificationsUrl, String labelsUrl, String releasesUrl, String createdAt, String updatedAt, String pushedAt, String gitUrl, String sshUrl, String cloneUrl, String svnUrl, Object homepage, long size, long stargazersCount, long watchersCount, String language, boolean hasIssues, boolean hasDownloads, boolean hasWiki, boolean hasPages, long forksCount, Object mirrorUrl, long openIssuesCount, long forks, long openIssues, long watchers, String defaultBranch) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this._private = _private;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.fork = fork;
        this.url = url;
        this.forksUrl = forksUrl;
        this.keysUrl = keysUrl;
        this.collaboratorsUrl = collaboratorsUrl;
        this.teamsUrl = teamsUrl;
        this.hooksUrl = hooksUrl;
        this.issueEventsUrl = issueEventsUrl;
        this.eventsUrl = eventsUrl;
        this.assigneesUrl = assigneesUrl;
        this.branchesUrl = branchesUrl;
        this.tagsUrl = tagsUrl;
        this.blobsUrl = blobsUrl;
        this.gitTagsUrl = gitTagsUrl;
        this.gitRefsUrl = gitRefsUrl;
        this.treesUrl = treesUrl;
        this.statusesUrl = statusesUrl;
        this.languagesUrl = languagesUrl;
        this.stargazersUrl = stargazersUrl;
        this.contributorsUrl = contributorsUrl;
        this.subscribersUrl = subscribersUrl;
        this.subscriptionUrl = subscriptionUrl;
        this.commitsUrl = commitsUrl;
        this.gitCommitsUrl = gitCommitsUrl;
        this.commentsUrl = commentsUrl;
        this.issueCommentUrl = issueCommentUrl;
        this.contentsUrl = contentsUrl;
        this.compareUrl = compareUrl;
        this.mergesUrl = mergesUrl;
        this.archiveUrl = archiveUrl;
        this.downloadsUrl = downloadsUrl;
        this.issuesUrl = issuesUrl;
        this.pullsUrl = pullsUrl;
        this.milestonesUrl = milestonesUrl;
        this.notificationsUrl = notificationsUrl;
        this.labelsUrl = labelsUrl;
        this.releasesUrl = releasesUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pushedAt = pushedAt;
        this.gitUrl = gitUrl;
        this.sshUrl = sshUrl;
        this.cloneUrl = cloneUrl;
        this.svnUrl = svnUrl;
        this.homepage = homepage;
        this.size = size;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.language = language;
        this.hasIssues = hasIssues;
        this.hasDownloads = hasDownloads;
        this.hasWiki = hasWiki;
        this.hasPages = hasPages;
        this.forksCount = forksCount;
        this.mirrorUrl = mirrorUrl;
        this.openIssuesCount = openIssuesCount;
        this.forks = forks;
        this.openIssues = openIssues;
        this.watchers = watchers;
        this.defaultBranch = defaultBranch;
    }

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     * The owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     * The _private
     */
    public boolean isPrivate() {
        return _private;
    }

    /**
     *
     * @param _private
     * The private
     */
    public void setPrivate(boolean _private) {
        this._private = _private;
    }

    /**
     *
     * @return
     * The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     *
     * @param htmlUrl
     * The html_url
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The fork
     */
    public boolean isFork() {
        return fork;
    }

    /**
     *
     * @param fork
     * The fork
     */
    public void setFork(boolean fork) {
        this.fork = fork;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The forksUrl
     */
    public String getForksUrl() {
        return forksUrl;
    }

    /**
     *
     * @param forksUrl
     * The forks_url
     */
    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    /**
     *
     * @return
     * The keysUrl
     */
    public String getKeysUrl() {
        return keysUrl;
    }

    /**
     *
     * @param keysUrl
     * The keys_url
     */
    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    /**
     *
     * @return
     * The collaboratorsUrl
     */
    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    /**
     *
     * @param collaboratorsUrl
     * The collaborators_url
     */
    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    /**
     *
     * @return
     * The teamsUrl
     */
    public String getTeamsUrl() {
        return teamsUrl;
    }

    /**
     *
     * @param teamsUrl
     * The teams_url
     */
    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    /**
     *
     * @return
     * The hooksUrl
     */
    public String getHooksUrl() {
        return hooksUrl;
    }

    /**
     *
     * @param hooksUrl
     * The hooks_url
     */
    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    /**
     *
     * @return
     * The issueEventsUrl
     */
    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    /**
     *
     * @param issueEventsUrl
     * The issue_events_url
     */
    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    /**
     *
     * @return
     * The eventsUrl
     */
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     *
     * @param eventsUrl
     * The events_url
     */
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    /**
     *
     * @return
     * The assigneesUrl
     */
    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    /**
     *
     * @param assigneesUrl
     * The assignees_url
     */
    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    /**
     *
     * @return
     * The branchesUrl
     */
    public String getBranchesUrl() {
        return branchesUrl;
    }

    /**
     *
     * @param branchesUrl
     * The branches_url
     */
    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    /**
     *
     * @return
     * The tagsUrl
     */
    public String getTagsUrl() {
        return tagsUrl;
    }

    /**
     *
     * @param tagsUrl
     * The tags_url
     */
    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    /**
     *
     * @return
     * The blobsUrl
     */
    public String getBlobsUrl() {
        return blobsUrl;
    }

    /**
     *
     * @param blobsUrl
     * The blobs_url
     */
    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    /**
     *
     * @return
     * The gitTagsUrl
     */
    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    /**
     *
     * @param gitTagsUrl
     * The git_tags_url
     */
    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    /**
     *
     * @return
     * The gitRefsUrl
     */
    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    /**
     *
     * @param gitRefsUrl
     * The git_refs_url
     */
    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    /**
     *
     * @return
     * The treesUrl
     */
    public String getTreesUrl() {
        return treesUrl;
    }

    /**
     *
     * @param treesUrl
     * The trees_url
     */
    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    /**
     *
     * @return
     * The statusesUrl
     */
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     *
     * @param statusesUrl
     * The statuses_url
     */
    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    /**
     *
     * @return
     * The languagesUrl
     */
    public String getLanguagesUrl() {
        return languagesUrl;
    }

    /**
     *
     * @param languagesUrl
     * The languages_url
     */
    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    /**
     *
     * @return
     * The stargazersUrl
     */
    public String getStargazersUrl() {
        return stargazersUrl;
    }

    /**
     *
     * @param stargazersUrl
     * The stargazers_url
     */
    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    /**
     *
     * @return
     * The contributorsUrl
     */
    public String getContributorsUrl() {
        return contributorsUrl;
    }

    /**
     *
     * @param contributorsUrl
     * The contributors_url
     */
    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    /**
     *
     * @return
     * The subscribersUrl
     */
    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    /**
     *
     * @param subscribersUrl
     * The subscribers_url
     */
    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    /**
     *
     * @return
     * The subscriptionUrl
     */
    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    /**
     *
     * @param subscriptionUrl
     * The subscription_url
     */
    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    /**
     *
     * @return
     * The commitsUrl
     */
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     *
     * @param commitsUrl
     * The commits_url
     */
    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    /**
     *
     * @return
     * The gitCommitsUrl
     */
    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    /**
     *
     * @param gitCommitsUrl
     * The git_commits_url
     */
    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    /**
     *
     * @return
     * The commentsUrl
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     *
     * @param commentsUrl
     * The comments_url
     */
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    /**
     *
     * @return
     * The issueCommentUrl
     */
    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    /**
     *
     * @param issueCommentUrl
     * The issue_comment_url
     */
    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    /**
     *
     * @return
     * The contentsUrl
     */
    public String getContentsUrl() {
        return contentsUrl;
    }

    /**
     *
     * @param contentsUrl
     * The contents_url
     */
    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    /**
     *
     * @return
     * The compareUrl
     */
    public String getCompareUrl() {
        return compareUrl;
    }

    /**
     *
     * @param compareUrl
     * The compare_url
     */
    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    /**
     *
     * @return
     * The mergesUrl
     */
    public String getMergesUrl() {
        return mergesUrl;
    }

    /**
     *
     * @param mergesUrl
     * The merges_url
     */
    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    /**
     *
     * @return
     * The archiveUrl
     */
    public String getArchiveUrl() {
        return archiveUrl;
    }

    /**
     *
     * @param archiveUrl
     * The archive_url
     */
    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    /**
     *
     * @return
     * The downloadsUrl
     */
    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    /**
     *
     * @param downloadsUrl
     * The downloads_url
     */
    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    /**
     *
     * @return
     * The issuesUrl
     */
    public String getIssuesUrl() {
        return issuesUrl;
    }

    /**
     *
     * @param issuesUrl
     * The issues_url
     */
    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    /**
     *
     * @return
     * The pullsUrl
     */
    public String getPullsUrl() {
        return pullsUrl;
    }

    /**
     *
     * @param pullsUrl
     * The pulls_url
     */
    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    /**
     *
     * @return
     * The milestonesUrl
     */
    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    /**
     *
     * @param milestonesUrl
     * The milestones_url
     */
    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    /**
     *
     * @return
     * The notificationsUrl
     */
    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    /**
     *
     * @param notificationsUrl
     * The notifications_url
     */
    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    /**
     *
     * @return
     * The labelsUrl
     */
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     *
     * @param labelsUrl
     * The labels_url
     */
    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    /**
     *
     * @return
     * The releasesUrl
     */
    public String getReleasesUrl() {
        return releasesUrl;
    }

    /**
     *
     * @param releasesUrl
     * The releases_url
     */
    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The pushedAt
     */
    public String getPushedAt() {
        return pushedAt;
    }

    /**
     *
     * @param pushedAt
     * The pushed_at
     */
    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    /**
     *
     * @return
     * The gitUrl
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     *
     * @param gitUrl
     * The git_url
     */
    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    /**
     *
     * @return
     * The sshUrl
     */
    public String getSshUrl() {
        return sshUrl;
    }

    /**
     *
     * @param sshUrl
     * The ssh_url
     */
    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    /**
     *
     * @return
     * The cloneUrl
     */
    public String getCloneUrl() {
        return cloneUrl;
    }

    /**
     *
     * @param cloneUrl
     * The clone_url
     */
    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    /**
     *
     * @return
     * The svnUrl
     */
    public String getSvnUrl() {
        return svnUrl;
    }

    /**
     *
     * @param svnUrl
     * The svn_url
     */
    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    /**
     *
     * @return
     * The homepage
     */
    public Object getHomepage() {
        return homepage;
    }

    /**
     *
     * @param homepage
     * The homepage
     */
    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    /**
     *
     * @return
     * The size
     */
    public long getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The size
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     *
     * @return
     * The stargazersCount
     */
    public long getStargazersCount() {
        return stargazersCount;
    }

    /**
     *
     * @param stargazersCount
     * The stargazers_count
     */
    public void setStargazersCount(long stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    /**
     *
     * @return
     * The watchersCount
     */
    public long getWatchersCount() {
        return watchersCount;
    }

    /**
     *
     * @param watchersCount
     * The watchers_count
     */
    public void setWatchersCount(long watchersCount) {
        this.watchersCount = watchersCount;
    }

    /**
     *
     * @return
     * The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     * The hasIssues
     */
    public boolean isHasIssues() {
        return hasIssues;
    }

    /**
     *
     * @param hasIssues
     * The has_issues
     */
    public void setHasIssues(boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    /**
     *
     * @return
     * The hasDownloads
     */
    public boolean isHasDownloads() {
        return hasDownloads;
    }

    /**
     *
     * @param hasDownloads
     * The has_downloads
     */
    public void setHasDownloads(boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    /**
     *
     * @return
     * The hasWiki
     */
    public boolean isHasWiki() {
        return hasWiki;
    }

    /**
     *
     * @param hasWiki
     * The has_wiki
     */
    public void setHasWiki(boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    /**
     *
     * @return
     * The hasPages
     */
    public boolean isHasPages() {
        return hasPages;
    }

    /**
     *
     * @param hasPages
     * The has_pages
     */
    public void setHasPages(boolean hasPages) {
        this.hasPages = hasPages;
    }

    /**
     *
     * @return
     * The forksCount
     */
    public long getForksCount() {
        return forksCount;
    }

    /**
     *
     * @param forksCount
     * The forks_count
     */
    public void setForksCount(long forksCount) {
        this.forksCount = forksCount;
    }

    /**
     *
     * @return
     * The mirrorUrl
     */
    public Object getMirrorUrl() {
        return mirrorUrl;
    }

    /**
     *
     * @param mirrorUrl
     * The mirror_url
     */
    public void setMirrorUrl(Object mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    /**
     *
     * @return
     * The openIssuesCount
     */
    public long getOpenIssuesCount() {
        return openIssuesCount;
    }

    /**
     *
     * @param openIssuesCount
     * The open_issues_count
     */
    public void setOpenIssuesCount(long openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    /**
     *
     * @return
     * The forks
     */
    public long getForks() {
        return forks;
    }

    /**
     *
     * @param forks
     * The forks
     */
    public void setForks(long forks) {
        this.forks = forks;
    }

    /**
     *
     * @return
     * The openIssues
     */
    public long getOpenIssues() {
        return openIssues;
    }

    /**
     *
     * @param openIssues
     * The open_issues
     */
    public void setOpenIssues(long openIssues) {
        this.openIssues = openIssues;
    }

    /**
     *
     * @return
     * The watchers
     */
    public long getWatchers() {
        return watchers;
    }

    /**
     *
     * @param watchers
     * The watchers
     */
    public void setWatchers(long watchers) {
        this.watchers = watchers;
    }

    /**
     *
     * @return
     * The defaultBranch
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     *
     * @param defaultBranch
     * The default_branch
     */
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }


    @Override
    public String toString() {
        return "GitHub{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", owner=" + owner +
                ", _private=" + _private +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", description='" + description + '\'' +
                ", fork=" + fork +
                ", url='" + url + '\'' +
                ", forksUrl='" + forksUrl + '\'' +
                ", keysUrl='" + keysUrl + '\'' +
                ", collaboratorsUrl='" + collaboratorsUrl + '\'' +
                ", teamsUrl='" + teamsUrl + '\'' +
                ", hooksUrl='" + hooksUrl + '\'' +
                ", issueEventsUrl='" + issueEventsUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", assigneesUrl='" + assigneesUrl + '\'' +
                ", branchesUrl='" + branchesUrl + '\'' +
                ", tagsUrl='" + tagsUrl + '\'' +
                ", blobsUrl='" + blobsUrl + '\'' +
                ", gitTagsUrl='" + gitTagsUrl + '\'' +
                ", gitRefsUrl='" + gitRefsUrl + '\'' +
                ", treesUrl='" + treesUrl + '\'' +
                ", statusesUrl='" + statusesUrl + '\'' +
                ", languagesUrl='" + languagesUrl + '\'' +
                ", stargazersUrl='" + stargazersUrl + '\'' +
                ", contributorsUrl='" + contributorsUrl + '\'' +
                ", subscribersUrl='" + subscribersUrl + '\'' +
                ", subscriptionUrl='" + subscriptionUrl + '\'' +
                ", commitsUrl='" + commitsUrl + '\'' +
                ", gitCommitsUrl='" + gitCommitsUrl + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", issueCommentUrl='" + issueCommentUrl + '\'' +
                ", contentsUrl='" + contentsUrl + '\'' +
                ", compareUrl='" + compareUrl + '\'' +
                ", mergesUrl='" + mergesUrl + '\'' +
                ", archiveUrl='" + archiveUrl + '\'' +
                ", downloadsUrl='" + downloadsUrl + '\'' +
                ", issuesUrl='" + issuesUrl + '\'' +
                ", pullsUrl='" + pullsUrl + '\'' +
                ", milestonesUrl='" + milestonesUrl + '\'' +
                ", notificationsUrl='" + notificationsUrl + '\'' +
                ", labelsUrl='" + labelsUrl + '\'' +
                ", releasesUrl='" + releasesUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", pushedAt='" + pushedAt + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", sshUrl='" + sshUrl + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", svnUrl='" + svnUrl + '\'' +
                ", homepage=" + homepage +
                ", size=" + size +
                ", stargazersCount=" + stargazersCount +
                ", watchersCount=" + watchersCount +
                ", language='" + language + '\'' +
                ", hasIssues=" + hasIssues +
                ", hasDownloads=" + hasDownloads +
                ", hasWiki=" + hasWiki +
                ", hasPages=" + hasPages +
                ", forksCount=" + forksCount +
                ", mirrorUrl=" + mirrorUrl +
                ", openIssuesCount=" + openIssuesCount +
                ", forks=" + forks +
                ", openIssues=" + openIssues +
                ", watchers=" + watchers +
                ", defaultBranch='" + defaultBranch + '\'' +
                '}';
    }
}