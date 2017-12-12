<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Storyboard</title>
    <link rel="stylesheet" href="/css/main.css">
    <script src="https://use.fontawesome.com/4f32504cfe.js"></script>
</head>
<body>
<h3>${storyboard.title}</h3>

<h3>Add user to storyboard</h3>
<form action="/storyboards/${storyboard.id}/users" method="post">
    <input type="text" name="email" placeholder="Email"/>
    <input type="submit" value="Add"/>
</form>

<h3>All you swimlanes</h3>
<div class="inline-flex">
    <c:forEach var="swimlane" items="${storyboard.swimlanes}" varStatus="row">
        <div class="swimlane app-color">
            <p class="swimlane-title">${swimlane.name}</p>
            <div class="create-card text-center">
                <p>Create card</p>
                <form action="/storyboards/${storyboard.id}/swimlanes/${swimlane.id}/cards" method="post">
                    <div class="storyboard-text-box"><input placeholder="Name" type="text" name="name"/></div>
                    <div class="storyboard-text-box"><input placeholder="Description" type="text" name="description"/>
                    </div>
                    <div class="storyboard-button">
                        <input type="submit" value="Add"/>
                    </div>
                </form>
            </div>
            <div class="card-list">
                <h3> Card List</h3>
                <c:forEach var="card" items="${swimlane.cards}" varStatus="row">
                    <div class="card-form text-center">
                        <p>${card.title}</p>
                        <div>
                            <p>Add user to card</p>
                            <form action="/storyboards/${storyboard.id}/cards/${card.id}/users" method="post">
                                <div class="storyboard-text-box">
                                    <input placeholder="Email" type="text" name="email"/>
                                </div>
                                <div class="storyboard-button">
                                    <input class="text-box" type="submit" value="Add"/>
                                </div>
                            </form>
                        </div>
                        <form action="/storyboards/${storyboard.id}/cards/${card.id}" method="post">
                            <input type="hidden" name="_method" value="_delete">
                            <button type="submit"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<h3>Add swimlane</h3>
<div class="add-swimlane">
    <form action="/storyboards/${storyboard.id}/swimlanes" method="post">
        <div class="storyboard-text-box">
            <input placeholder="Name" type="text" id="name" name="name"/>
        </div>
        <div class="storyboard-button-1">
            <input type="submit" value="Add"/>
        </div>
    </form>
</div>


</body>
</html>
