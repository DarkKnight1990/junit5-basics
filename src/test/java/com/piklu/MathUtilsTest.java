package com.piklu;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;


@DisplayName("When running MathUtils")
class MathUtilsTest {
	
	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
	}
	
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up...");
	}
	
	
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest {
		@Test
		@DisplayName("when adding two positive numbers")
		void testAddPositive() {
			int expected = 2;
			int actual = mathUtils.add(1, 1);
			assertEquals(expected, actual, () -> "should return the right sum");
		}
		
		@Test
		@DisplayName("when adding two negative numbers")
		void testAddNegative() {
			int expected = -2;
			int actual = mathUtils.add(-1, -1);
			assertEquals(expected, actual, () -> "should return sum " + expected + " but returned " + actual);
		}
	}
	
	@Test
	@DisplayName("Testing multiply method")
	@Tag("Math")
	void testMultiply() {
		assertAll(
				() -> assertEquals(4,  mathUtils.multiply(2, 2)),
				() -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(-2, mathUtils.multiply(2,  -1))
				);
	}
	
	@Test
	@DisplayName("Testing divide function")
	@Tag("Math")
	void testDivide() {
		boolean isServerUp = false;
		
		assumeTrue(isServerUp);
		
		int expected = 3;
		int actual = mathUtils.divide(6, 2);
		assertEquals(expected, actual, "should return the right divisible");
	}
	
	@Test
	@DisplayName("Testing divide by zero function")
	@Tag("Math")
	void testDivideByZero() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,  0), "divide by zero should throw error");
	}
	
	@Test
	@DisplayName("Testing compute circle area")
	@Tag("Circle")
	void testComputeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "should return right circle area");
	}
	
	@Test
	@DisplayName("Testing @Disabled annotation")
	@Disabled
	void testDisabledAnnotation() {
		fail("This test should be disabled");
	}

}
