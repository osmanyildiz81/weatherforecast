<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Weather Forecast</title>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
    </link>
    <script src="webjars/jquery/3.4.1/jquery.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.js"> </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('.text-dark').each(function () {
                $('this').attr("src", $('.this').attr('src'));
            });
            $('.text-dark').each(function () {
                $(this).html('On : ' + new Date(parseInt($(this).text(), 10) * 1000).toLocaleString());
            });
        });
    </script>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</head>

<body>
    <div class="container-fluid">
        <div class="jumbotron">
            <div class="row">
                <c:forEach items="${weathers}" var="weather">
                    <div class="col">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                            <div class="panel-heading"><span class="lead"><b>Weather Conditions for:
                                        ${weather.name},${weather.cod}, ${weather.sys.country} </b><br />
                                    <div class="text-dark"><b>${weather.dt}</b></div>
                                </span></div>

                            <ul>
                                <li class="list-group-item">Temperature: ${weather.main.temp}</li>
                                <li class="list-group-item">Condition: ${weather.weather[0].description}</li>
                                <li class="list-group-item">Humidity: ${weather.main.humidity}</li>
                                <li class="list-group-item">Wind Speed: ${weather.wind.speed}</li>
                            </ul>
                        </div>
                        <div class="text-center">
                            <img src=${weather.weather[0].iconPath} class="rounded" />
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <form:form  modelAttribute="city" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="cityName">City Name</label>
                        <form:input id="cityName" path="cityName" type="text" class="form:input-large" required="true"/>

                        <div class="invalid-feedback">
                            Please provide a valid city.
                        </div>
                    </div>

                    <input type="submit" id="btnAdd" class="btn btn-primary" value ="Get Weather"/>
                </form:form>
            </div>
        </div>
        <br><br>
         <div class="row">
                        <c:forEach items="${cityWeather}" var="weather">
                            <div class="col">
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading"><span class="lead"><b>Weather Conditions for:
                                                ${weather.name},${weather.cod}, ${weather.sys.country} </b><br />
                                            <div class="text-dark"><b>${weather.dt}</b></div>
                                        </span></div>

                                    <ul>
                                        <li class="list-group-item">Temperature: ${weather.main.temp}</li>
                                        <li class="list-group-item">Condition: ${weather.weather[0].description}</li>
                                        <li class="list-group-item">Humidity: ${weather.main.humidity}</li>
                                        <li class="list-group-item">Wind Speed: ${weather.wind.speed}</li>
                                    </ul>
                                </div>
                                <div class="text-center">
                                    <img src=${weather.weather[0].iconPath} class="rounded" />
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                     <br><br>
        <div class="row">
            <div class="col">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">List of Weather Froecasts </span></div>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>City Name</th>
                                <th>Temperature</th>
                                <th>Weather Condition</th>
                                <th>Humidity</th>
                                <th>Wind Speed</th>
                                <th>Country</th>
                                <th>Date Time</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${simpleWeathers}" var="weather">
                                <tr>
                                    <td>${weather.id}</td>
                                    <td>${weather.city_name}</td>
                                    <td>${weather.temperature}</td>
                                    <td>${weather.weather_condition}</td>
                                    <td>${weather.humidity}</td>
                                    <td>${weather.wind_speed}</td>
                                    <td>${weather.country}</td>
                                    <td>${weather.date_time}</td>
                                  <!--    <td><a href="<c:url value='/delete-user-${user.ssoId}' />"
                                            class="btn btn-danger custom-width">delete</a></td>  -->
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>

</body>

</html>
