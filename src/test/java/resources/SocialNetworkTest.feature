Feature: User can make a post and comment on social network website.

  Scenario: Create a new post
    Given the base API URL is "https://jsonplaceholder.typicode.com/"
    When a user make a post request to "/posts" with the following data:
      | userId | id | title            | body                    |
      | 5      | 5  |New Post Title|this is post number 1|
    Then the response status code should be 201

  Scenario: Comment on a post
    Given a post with title "New Post Title" exists
    When a user makes a POST request to "/comments" with the following data:
      | postId | id | name            | email                 | body                |
      | 1      | 1  |Comment Title|comment@example.com|This is a comment|
    Then the response status code should be 201

  Scenario: List all users
    When a user makes a GET request to "/users"
    Then the response status code should be 200
    And zipcode should be numeric
    And zipcode should be > than 0

