$(document).ready(function() {

    var $articles = $("#container"),
        articleData = null;

    $.getJSON("rest/article", function(data) {
        articleData = data.article;

        for (var i = 0; i < articleData.length; i++) {
            var articleItem = articleData[i];
            $articles.append('<article><h2 class="articleHeading">' + articleItem.id + '</h2><p class="author">published by Sevdalin Zhelyazkov</p><p class="articleContent">' + articleItem.text + '</p></article>');
        }
    });

    $('#logout-button').click(function() {
        $.ajax({
            url: 'rest/user/logout',
            type: "GET",
            statusCode: {
                204: function() {
                    alert("Успешно излизане от системата!");
                    window.location.replace("index.html");
                }
            }
        });
    });
});
