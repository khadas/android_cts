/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Don't edit this file!  It is auto-generated by frameworks/rs/api/gen_runtime.

package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
import android.renderscript.Element;

public class TestFastLength extends RSBaseCompute {

    private ScriptC_TestFastLength script;
    private ScriptC_TestFastLengthRelaxed scriptRelaxed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script = new ScriptC_TestFastLength(mRS);
        scriptRelaxed = new ScriptC_TestFastLengthRelaxed(mRS);
    }

    public class ArgumentsFloatFloat {
        public float inV;
        public Floaty out;
    }

    private void checkFastLengthFloatFloat() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0xebac65aea2660e8fl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.forEach_testFastLengthFloatFloat(inV, out);
            verifyResultsFastLengthFloatFloat(inV, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloatFloat: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.forEach_testFastLengthFloatFloat(inV, out);
            verifyResultsFastLengthFloatFloat(inV, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloatFloat: " + e.toString());
        }
    }

    private void verifyResultsFastLengthFloatFloat(Allocation inV, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 1];
        inV.copyTo(arrayInV);
        float[] arrayOut = new float[INPUTSIZE * 1];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            ArgumentsFloatFloat args = new ArgumentsFloatFloat();
            // Create the appropriate sized arrays in args
            // Fill args with the input values
            args.inV = arrayInV[i];
            Floaty.setRelaxed(relaxed);
            CoreMathVerifier.computeFastLength(args);

            // Compare the expected outputs to the actual values returned by RS.
            boolean valid = true;
            if (!args.out.couldBe(arrayOut[i])) {
                valid = false;
            }
            if (!valid) {
                StringBuilder message = new StringBuilder();
                message.append("Input inV: ");
                message.append(String.format("%14.8g %8x %15a",
                        arrayInV[i], Float.floatToRawIntBits(arrayInV[i]), arrayInV[i]));
                message.append("\n");
                message.append("Expected output out: ");
                message.append(args.out.toString());
                message.append("\n");
                message.append("Actual   output out: ");
                message.append(String.format("%14.8g %8x %15a",
                        arrayOut[i], Float.floatToRawIntBits(arrayOut[i]), arrayOut[i]));
                if (!args.out.couldBe(arrayOut[i])) {
                    message.append(" FAIL");
                }
                message.append("\n");
                assertTrue("Incorrect output for checkFastLengthFloatFloat" +
                        (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
            }
        }
    }

    public class ArgumentsFloatNFloat {
        public float[] inV;
        public Floaty out;
    }

    private void checkFastLengthFloat2Float() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0x95f43650f85e6cadl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.forEach_testFastLengthFloat2Float(inV, out);
            verifyResultsFastLengthFloat2Float(inV, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloat2Float: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.forEach_testFastLengthFloat2Float(inV, out);
            verifyResultsFastLengthFloat2Float(inV, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloat2Float: " + e.toString());
        }
    }

    private void verifyResultsFastLengthFloat2Float(Allocation inV, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 2];
        inV.copyTo(arrayInV);
        float[] arrayOut = new float[INPUTSIZE * 1];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            ArgumentsFloatNFloat args = new ArgumentsFloatNFloat();
            // Create the appropriate sized arrays in args
            args.inV = new float[2];
            // Fill args with the input values
            for (int j = 0; j < 2 ; j++) {
                args.inV[j] = arrayInV[i * 2 + j];
            }
            Floaty.setRelaxed(relaxed);
            CoreMathVerifier.computeFastLength(args);

            // Compare the expected outputs to the actual values returned by RS.
            boolean valid = true;
            if (!args.out.couldBe(arrayOut[i])) {
                valid = false;
            }
            if (!valid) {
                StringBuilder message = new StringBuilder();
                for (int j = 0; j < 2 ; j++) {
                    message.append("Input inV: ");
                    message.append(String.format("%14.8g %8x %15a",
                            arrayInV[i * 2 + j], Float.floatToRawIntBits(arrayInV[i * 2 + j]), arrayInV[i * 2 + j]));
                    message.append("\n");
                }
                message.append("Expected output out: ");
                message.append(args.out.toString());
                message.append("\n");
                message.append("Actual   output out: ");
                message.append(String.format("%14.8g %8x %15a",
                        arrayOut[i], Float.floatToRawIntBits(arrayOut[i]), arrayOut[i]));
                if (!args.out.couldBe(arrayOut[i])) {
                    message.append(" FAIL");
                }
                message.append("\n");
                assertTrue("Incorrect output for checkFastLengthFloat2Float" +
                        (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
            }
        }
    }

    private void checkFastLengthFloat3Float() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x95f440f25764fb0el, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.forEach_testFastLengthFloat3Float(inV, out);
            verifyResultsFastLengthFloat3Float(inV, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloat3Float: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.forEach_testFastLengthFloat3Float(inV, out);
            verifyResultsFastLengthFloat3Float(inV, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloat3Float: " + e.toString());
        }
    }

    private void verifyResultsFastLengthFloat3Float(Allocation inV, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 4];
        inV.copyTo(arrayInV);
        float[] arrayOut = new float[INPUTSIZE * 1];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            ArgumentsFloatNFloat args = new ArgumentsFloatNFloat();
            // Create the appropriate sized arrays in args
            args.inV = new float[3];
            // Fill args with the input values
            for (int j = 0; j < 3 ; j++) {
                args.inV[j] = arrayInV[i * 4 + j];
            }
            Floaty.setRelaxed(relaxed);
            CoreMathVerifier.computeFastLength(args);

            // Compare the expected outputs to the actual values returned by RS.
            boolean valid = true;
            if (!args.out.couldBe(arrayOut[i])) {
                valid = false;
            }
            if (!valid) {
                StringBuilder message = new StringBuilder();
                for (int j = 0; j < 3 ; j++) {
                    message.append("Input inV: ");
                    message.append(String.format("%14.8g %8x %15a",
                            arrayInV[i * 4 + j], Float.floatToRawIntBits(arrayInV[i * 4 + j]), arrayInV[i * 4 + j]));
                    message.append("\n");
                }
                message.append("Expected output out: ");
                message.append(args.out.toString());
                message.append("\n");
                message.append("Actual   output out: ");
                message.append(String.format("%14.8g %8x %15a",
                        arrayOut[i], Float.floatToRawIntBits(arrayOut[i]), arrayOut[i]));
                if (!args.out.couldBe(arrayOut[i])) {
                    message.append(" FAIL");
                }
                message.append("\n");
                assertTrue("Incorrect output for checkFastLengthFloat3Float" +
                        (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
            }
        }
    }

    private void checkFastLengthFloat4Float() {
        Allocation inV = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0x95f44b93b66b896fl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.forEach_testFastLengthFloat4Float(inV, out);
            verifyResultsFastLengthFloat4Float(inV, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloat4Float: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.forEach_testFastLengthFloat4Float(inV, out);
            verifyResultsFastLengthFloat4Float(inV, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFastLengthFloat4Float: " + e.toString());
        }
    }

    private void verifyResultsFastLengthFloat4Float(Allocation inV, Allocation out, boolean relaxed) {
        float[] arrayInV = new float[INPUTSIZE * 4];
        inV.copyTo(arrayInV);
        float[] arrayOut = new float[INPUTSIZE * 1];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            ArgumentsFloatNFloat args = new ArgumentsFloatNFloat();
            // Create the appropriate sized arrays in args
            args.inV = new float[4];
            // Fill args with the input values
            for (int j = 0; j < 4 ; j++) {
                args.inV[j] = arrayInV[i * 4 + j];
            }
            Floaty.setRelaxed(relaxed);
            CoreMathVerifier.computeFastLength(args);

            // Compare the expected outputs to the actual values returned by RS.
            boolean valid = true;
            if (!args.out.couldBe(arrayOut[i])) {
                valid = false;
            }
            if (!valid) {
                StringBuilder message = new StringBuilder();
                for (int j = 0; j < 4 ; j++) {
                    message.append("Input inV: ");
                    message.append(String.format("%14.8g %8x %15a",
                            arrayInV[i * 4 + j], Float.floatToRawIntBits(arrayInV[i * 4 + j]), arrayInV[i * 4 + j]));
                    message.append("\n");
                }
                message.append("Expected output out: ");
                message.append(args.out.toString());
                message.append("\n");
                message.append("Actual   output out: ");
                message.append(String.format("%14.8g %8x %15a",
                        arrayOut[i], Float.floatToRawIntBits(arrayOut[i]), arrayOut[i]));
                if (!args.out.couldBe(arrayOut[i])) {
                    message.append(" FAIL");
                }
                message.append("\n");
                assertTrue("Incorrect output for checkFastLengthFloat4Float" +
                        (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
            }
        }
    }

    public void testFastLength() {
        checkFastLengthFloatFloat();
        checkFastLengthFloat2Float();
        checkFastLengthFloat3Float();
        checkFastLengthFloat4Float();
    }
}
