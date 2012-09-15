When /^I try to login without any credentials$/ do
  login("","","")
end

Then /^I should be asked to fill in mandatory values$/ do
  validate_mandatory_field_error_message
end

When /^I login with invalid credentials$/ do
  login("invalid","invalid","invalid")
end

Then /^I should not be logged-in$/ do
  validate_invalid_credentials_error_message
end

When /^I login with valid credentials$/ do
  login("valid","valid","valid")
end

Then /^I search the status for story number "([^"]*)"$/ do |story_number|
  search_card_in_standard_text_feature(story_number)
end
