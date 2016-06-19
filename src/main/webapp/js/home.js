$(document).ready(function() {

    var $techNews = $("#techNews"),
        $universityNews = $("#universityNews"),
        $articleManager = $("#articleManager"),
        articleData = null;

    $.getJSON("rest/article", function(data) {
        articleData = data.article;

        for (var i = 0; i < articleData.length; i++) {
            var articleItem = articleData[i];
            if (articleItem.articleType == "TechNews") {
                $techNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted by ' + articleItem.author + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            } else {
                $universityNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted by ' + articleItem.author + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            }
            $articleManager.append('<tr><td>' + articleItem.title + '</td><td>' + articleItem.articleType + '</td><td><a href="#" class="btn btn-warning" role="button">Edit</a></td><td><a href="#" id="deleteArticle" class="btn btn-danger" role="button" data-id="' + articleItem.id + '">Delete</a></td>');
        }
    });

    $('nav a').click(function() {
        var tab_id = $(this).attr('data-tab');

        $('.tab-content').removeClass('current');

        $("." + tab_id).addClass('current');
    });


    var $AddButton = $("#add-button");
    $AddButton.click(function() {
        var oArticleData = {
            article: {
                title: $("#article-title").val(),
                articleType: $('#category option:selected').text().replace(/ /g, ''),
                text: $("#article-body").val()
            }
        };

        $.ajax({
                url: 'rest/article/add',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(oArticleData)
            })
            .success(function(data) {
                alert("Added successfully!");
            })
            .fail(function(data) {
                alert("Failed to add article!");
            })
            .always(function() {
                $("#add-form").submit();
            });
    });

    $('#logout-button').click(function() {
        $.ajax({
            url: 'rest/user/logout',
            type: "GET",
            statusCode: {
                204: function() {
                    window.location.replace("index.html");
                }
            }
        });
    });
});

$(document).ready(function() {
    $('body').on('click', '#deleteArticle', function() {
        var deleteID = $(this).attr('data-id');
        $.ajax({
            type: "DELETE",
            url: "rest/article/delete?id=" + deleteID,
            async: true,
            complete: function() {
                alert("Deleted successfully!");
            }
        });
        location.reload();
    });
});
