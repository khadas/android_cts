#
# Copyright (C) 2010 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Makefile for producing CTS coverage reports.
# Run "make cts-test-coverage" in the $ANDROID_BUILD_TOP directory.

include cts/CtsTestCaseList.mk

cts_testcases_out_apk := $(wildcard $(CTS_TESTCASES_OUT)/*.apk)
cts_testcases_out_jar := $(wildcard $(CTS_TESTCASES_OUT)/*.jar)
cts_verifier_apk := $(call intermediates-dir-for,APPS,CtsVerifier)/package.apk

cts_api_coverage_exe := $(HOST_OUT_EXECUTABLES)/cts-api-coverage
dexdeps_exe := $(HOST_OUT_EXECUTABLES)/dexdeps

coverage_out := $(HOST_OUT)/cts-api-coverage

api_text_description := frameworks/base/api/current.txt
api_xml_description := $(coverage_out)/api.xml
$(api_xml_description) : $(api_text_description) $(APICHECK)
	$(hide) echo "Converting API file to XML: $@"
	$(hide) mkdir -p $(dir $@)
	$(hide) $(APICHECK_COMMAND) -convert2xml $< $@

cts-test-coverage-report := $(coverage_out)/test-coverage.html
cts-verifier-coverage-report := $(coverage_out)/verifier-coverage.html
cts-combined-coverage-report := $(coverage_out)/combined-coverage.html
cts-combined-xml-coverage-report := $(coverage_out)/combined-coverage.xml

cts_api_coverage_dependencies := $(cts_api_coverage_exe) $(dexdeps_exe) $(api_xml_description)

cts_coverage_test_cases_dependencies := $(cts_testcases_out_apk) $(cts_testcases_out_jar)
$(cts-test-coverage-report): PRIVATE_TEST_CASES_APKS := $(cts_coverage_test_cases_dependencies)
$(cts-test-coverage-report): PRIVATE_CTS_API_COVERAGE_EXE := $(cts_api_coverage_exe)
$(cts-test-coverage-report): PRIVATE_DEXDEPS_EXE := $(dexdeps_exe)
$(cts-test-coverage-report): PRIVATE_API_XML_DESC := $(api_xml_description)
$(cts-test-coverage-report) : $(cts_coverage_test_cases_dependencies) $(cts_api_coverage_dependencies) | $(ACP)
	$(call generate-coverage-report,"CTS Tests API Coverage Report",\
			$(PRIVATE_TEST_CASES_APKS),html)

cts_coverage_test_cases_dependencies := $(cts_verifier_apk)
$(cts-verifier-coverage-report): PRIVATE_TEST_CASES_APKS := $(cts_coverage_test_cases_dependencies)
$(cts-verifier-coverage-report): PRIVATE_CTS_API_COVERAGE_EXE := $(cts_api_coverage_exe)
$(cts-verifier-coverage-report): PRIVATE_DEXDEPS_EXE := $(dexdeps_exe)
$(cts-verifier-coverage-report): PRIVATE_API_XML_DESC := $(api_xml_description)
$(cts-verifier-coverage-report) : $(cts_coverage_test_cases_dependencies) $(cts_api_coverage_dependencies) | $(ACP)
	$(call generate-coverage-report,"CTS Verifier API Coverage Report",\
			$(PRIVATE_TEST_CASES_APKS),html)

cts_coverage_test_cases_dependencies := $(cts_testcases_out_apk) $(cts_testcases_out_jar) $(cts_verifier)
$(cts-combined-coverage-report): PRIVATE_TEST_CASES_APKS := $(cts_coverage_test_cases_dependencies)
$(cts-combined-coverage-report): PRIVATE_CTS_API_COVERAGE_EXE := $(cts_api_coverage_exe)
$(cts-combined-coverage-report): PRIVATE_DEXDEPS_EXE := $(dexdeps_exe)
$(cts-combined-coverage-report): PRIVATE_API_XML_DESC := $(api_xml_description)
$(cts-combined-coverage-report) : $(cts_coverage_test_cases_dependencies) $(cts_api_coverage_dependencies) | $(ACP)
	$(call generate-coverage-report,"CTS Combined API Coverage Report",\
			$(PRIVATE_TEST_CASES_APKS),html)

cts_coverage_test_cases_dependencies := $(cts_testcases_out_apk) $(cts_testcases_out_jar) $(cts_verifier)
$(cts-combined-xml-coverage-report): PRIVATE_TEST_CASES_APKS := $(cts_coverage_test_cases_dependencies)
$(cts-combined-xml-coverage-report): PRIVATE_CTS_API_COVERAGE_EXE := $(cts_api_coverage_exe)
$(cts-combined-xml-coverage-report): PRIVATE_DEXDEPS_EXE := $(dexdeps_exe)
$(cts-combined-xml-coverage-report): PRIVATE_API_XML_DESC := $(api_xml_description)
$(cts-combined-xml-coverage-report) : $(cts_coverage_test_cases_dependencies) $(cts_api_coverage_dependencies) | $(ACP)
	$(call generate-coverage-report,"CTS Combined API Coverage Report - XML",\
			$(PRIVATE_TEST_CASES_APKS),xml)

.PHONY: cts-test-coverage
cts-test-coverage : $(cts-test-coverage-report)

.PHONY: cts-verifier-coverage
cts-verifier-coverage : $(cts-verifier-coverage-report)

.PHONY: cts-combined-coverage
cts-combined-coverage : $(cts-combined-coverage-report)

.PHONY: cts-combined-xml-coverage
cts-combined-xml-coverage : $(cts-combined-xml-coverage-report)

# Put the test coverage report in the dist dir if "cts" is among the build goals.
ifneq ($(filter cts, $(MAKECMDGOALS)),)
  $(call dist-for-goals, cts, $(cts-test-coverage-report):cts-test-coverage-report.html)
  $(call dist-for-goals, cts, $(cts-verifier-coverage-report):cts-verifier-coverage-report.html)
  $(call dist-for-goals, cts, $(cts-combined-coverage-report):cts-combined-coverage-report.html)
  $(call dist-for-goals, cts, $(cts-combined-xml-coverage-report):cts-combined-coverage-report.xml)
endif

# Arguments;
#  1 - Name of the report printed out on the screen
#  2 - List of apk files that will be scanned to generate the report
#  3 - Format of the report
define generate-coverage-report
	$(hide) mkdir -p $(dir $@)
	$(hide) $(PRIVATE_CTS_API_COVERAGE_EXE) -d $(PRIVATE_DEXDEPS_EXE) -a $(PRIVATE_API_XML_DESC) -f $(3) -o $@ $(2)
	@ echo $(1): file://$(ANDROID_BUILD_TOP)/$@
endef

# Reset temp vars
cts_api_coverage_dependencies :=
cts_coverage_test_cases_dependencies :=
cts-combined-coverage-report :=
cts-combined-xml-coverage-report :=
cts-verifier-coverage-report :=
cts-test-coverage-report :=
api_xml_description :=
api_text_description :=
coverage_out :=
dexdeps_exe :=
cts_api_coverage_exe :=
cts_testcases_out_apk :=
cts_testcases_out_jar :=
cts_verifier_apk :=
