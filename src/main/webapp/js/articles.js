$(document).ready(function() {

    var $techNews = $("#techNews"),
        $universityNews = $("#universityNews"),
        $articleManager = $("#articleManager"),
        userData = null,
        articleExist = false;

    $.getJSON("rest/user/current", function(data) {
        userData = data.user;
    });

    $.getJSON("rest/article", function(data) {
        articleData = data.article;

        for (var i = 0; i < articleData.length; i++) {
            var articleItem = articleData[i];
            if (articleItem.articleType == "TechNews" && articleItem.isPublished == 1) {
                $techNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted by ' + articleItem.author + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            } else if (articleItem.articleType == "UniversityNews" && articleItem.isPublished == 1) {
                $universityNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted by ' + articleItem.author + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            } else
            if (userData.admin === 1) {
                $articleManager.append('<tr><td>' + articleItem.title + '</td><td>' + articleItem.articleType + '</td><td><a href="javascript:;" id="approveArticle" class="btn btn-success" role="button" data-id="' + articleItem.id + '">Approve</a></td><td><a href="javascript:;" id="deleteArticle" class="btn btn-danger" role="button" data-id="' + articleItem.id + '">Delete</a></td>');

            } else {
                if (articleItem.author == userData.userName) {
                    articleExist = true;
                    $articleManager.append('<tr><td>' + articleItem.title + '</td><td>' + articleItem.articleType + '</td><td><a href="javascript:;" id="deleteArticle" class="btn btn-danger" role="button" data-id="' + articleItem.id + '">Delete</a></td>');
                }
            }
        }
        if (!articleExist) {
            $articleManager.append('<tr><td>No articles to show.</td></tr>');
        }

    });

    $('nav a').click(function() {
        var tab_id = $(this).attr('data-tab');

        $('.tab-content').removeClass('current');

        $("." + tab_id).addClass('current');
    });
});
