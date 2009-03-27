
/**
* <copyright>
*
* Copyright (c) 2009 Jin Mingjian and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Jin Mingjian - Initial API and implementation
*  
* </copyright>
* 
**/

package com.google.code.exquery.parser;

public class XQueryKWLexerprs implements lpg.runtime.ParseTable, XQueryKWLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 407;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 603;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 96;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 97;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 506;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 507;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return XQueryKWLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            2,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            6,7,8,6,9,7,14,8,5,7,
            8,6,8,7,5,5,8,5,15,11,
            7,10,9,8,2,6,6,8,12,2,
            8,6,2,3,3,5,2,6,9,10,
            4,5,9,4,4,4,2,3,2,5,
            9,6,8,2,5,8,4,8,3,6,
            5,10,4,18,17,9,6,8,17,9,
            16,9,8,3,4,3,2,2,2,2,
            2,2,2,9,7,13,7,14,2,4,
            4,22,16,14,4,10
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,99,81,
            28,36,155,172,76,24,54,46,67,80,
            100,57,107,13,83,89,162,29,103,71,
            115,34,165,175,177,179,181,109,182,184,
            188,190,185,86,192,193,60,195,122,197,
            201,202,116,137,203,205,206,208,125,40,
            127,209,210,211,216,93,131,214,218,133,
            221,136,219,224,141,226,230,235,236,237,
            239,242,245,246,247,252,254,256,250,257,
            258,263,267,269,262,147,259,271,274,275,
            276,279,280,283,288,285,289,292,290,299,
            293,297,302,304,144,305,308,309,312,316,
            314,317,152,319,315,325,326,329,330,332,
            335,336,338,341,340,347,343,349,350,351,
            357,355,359,361,362,363,367,370,371,369,
            372,377,381,382,373,384,388,387,391,393,
            394,397,395,398,154,402,406,410,412,404,
            414,415,417,419,420,421,423,430,431,433,
            422,435,438,426,437,440,445,449,439,454,
            77,456,458,457,447,461,462,466,467,469,
            470,474,475,478,482,157,484,476,485,480,
            488,490,492,497,496,493,501,503,505,507,
            510,509,515,511,517,514,521,523,525,528,
            524,533,532,534,535,538,540,542,541,544,
            547,550,552,554,555,561,557,562,564,567,
            568,569,574,575,576,577,581,582,584,585,
            588,591,592,595,598,600,596,604,606,607,
            609,610,616,612,617,623,619,625,626,631,
            627,632,634,636,638,637,640,639,644,649,
            653,159,656,658,662,664,650,657,164,643,
            665,667,669,671,673,674,677,680,681,682,
            683,684,686,693,697,701,687,692,696,705,
            708,704,709,710,712,713,714,719,725,717,
            723,727,728,730,729,733,737,731,738,743,
            745,744,751,752,753,755,756,757,758,760,
            762,759,763,771,769,770,779,780,782,785,
            786,787,788,795,798,799,800,801,805,806,
            790,807,809,810,812,811,817,819,821,822,
            824,825,831,837,833,839,835,840,842,844,
            847,849,851,854,853,856,862,863,865,855,
            866,874,872,876,880,868,870,878,882,886,
            885,887,893,888,898,507,507
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,0,13,14,15,16,17,18,7,
            8,21,22,0,24,2,3,0,0,6,
            3,3,20,0,11,0,1,14,10,0,
            5,18,14,8,5,0,1,2,15,21,
            22,23,7,0,19,20,0,1,2,0,
            7,8,3,7,8,9,0,1,2,10,
            0,5,19,14,15,0,0,2,3,0,
            0,6,0,1,5,0,4,2,0,1,
            8,6,0,23,15,7,8,18,6,0,
            24,9,0,1,19,25,0,8,0,7,
            18,5,13,7,0,0,0,9,4,11,
            5,0,8,2,0,10,0,1,7,5,
            0,7,0,1,8,0,0,2,6,3,
            0,11,12,0,9,5,0,1,2,6,
            10,0,9,0,0,4,0,1,0,1,
            4,0,9,0,0,7,15,4,15,8,
            7,0,1,19,0,1,0,1,0,1,
            0,0,2,0,0,2,22,0,1,0,
            9,0,0,4,0,4,0,1,6,15,
            0,0,0,2,0,0,2,0,0,0,
            0,11,10,0,6,0,22,0,0,14,
            0,1,13,0,9,0,19,14,11,0,
            5,21,9,15,0,0,0,1,0,5,
            2,0,13,8,0,0,0,3,7,0,
            5,0,1,0,1,0,0,0,0,13,
            5,0,0,14,2,8,0,1,0,1,
            0,10,14,0,0,0,6,21,0,0,
            1,6,0,10,0,7,4,0,0,0,
            1,0,0,9,20,4,0,10,0,1,
            4,0,1,0,0,1,18,0,0,17,
            3,0,9,0,0,0,0,1,0,1,
            9,13,7,10,0,0,1,13,0,0,
            1,0,1,5,0,0,2,0,14,0,
            0,4,0,8,4,6,0,1,0,0,
            0,3,2,11,0,6,0,1,0,5,
            0,0,0,3,6,4,0,5,0,0,
            0,0,0,7,5,5,0,1,7,11,
            0,0,2,0,12,2,0,0,7,2,
            0,5,0,0,0,2,0,0,8,0,
            8,0,6,0,3,0,1,10,14,0,
            1,0,1,0,0,12,0,1,0,0,
            0,0,0,3,11,0,7,5,14,0,
            0,13,0,1,0,6,0,0,0,0,
            10,20,5,5,0,20,0,11,0,15,
            11,7,4,0,1,0,0,0,3,2,
            0,0,2,17,3,0,0,11,0,0,
            4,6,3,0,0,0,2,0,5,0,
            3,0,1,0,0,17,3,0,4,0,
            1,0,0,2,7,0,0,18,2,24,
            0,6,0,11,0,5,0,1,0,0,
            0,7,3,0,0,13,0,1,10,5,
            0,1,0,0,0,3,2,0,15,19,
            3,0,0,0,0,12,4,0,7,0,
            0,0,8,0,5,4,0,4,8,0,
            17,0,1,0,0,2,0,20,9,13,
            0,0,2,0,10,4,0,0,0,13,
            7,3,6,0,0,0,0,4,11,4,
            0,0,8,0,0,4,6,0,12,5,
            0,0,5,3,0,0,2,0,1,0,
            1,6,11,0,21,0,0,2,0,0,
            7,0,3,7,6,0,0,2,0,3,
            2,10,0,1,0,0,0,1,3,5,
            0,0,2,0,1,0,0,0,0,0,
            9,2,0,0,9,8,3,5,0,0,
            14,3,0,1,16,0,0,0,2,4,
            3,0,1,0,0,1,0,4,0,20,
            0,1,0,0,1,9,0,1,10,0,
            0,0,0,0,1,0,0,2,16,9,
            11,0,0,12,2,0,0,6,16,3,
            0,1,16,0,0,10,2,0,0,0,
            3,0,0,0,3,3,0,4,0,16,
            12,12,0,5,0,1,0,0,0,0,
            0,4,0,1,8,6,0,0,2,23,
            12,19,0,0,0,2,16,3,6,12,
            0,0,0,2,0,0,0,0,0,0,
            5,0,0,5,8,6,16,13,0,0,
            0,0,15,21,12,14,6,8,0,0,
            1,0,1,15,0,0,0,0,3,0,
            12,4,3,7,0,1,12,0,0,0,
            0,4,4,4,0,0,0,7,0,0,
            0,0,6,8,3,11,0,9,0,3,
            0,0,1,0,0,0,17,17,5,9,
            0,1,0,9,0,17,0,1,0,0,
            2,0,10,0,1,6,0,13,0,1,
            0,10,0,0,0,0,4,2,12,6,
            10,0,0,2,0,0,4,0,3,0,
            6,0,18,0,1,0,5,0,3,0,
            1,0,13,16,0,0,0,0,2,0,
            0,10,0,16,9,8,4,0,0,0,
            3,0,18,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            507,117,101,115,105,103,111,104,109,106,
            107,114,507,100,116,112,108,113,110,151,
            152,118,119,507,102,130,133,507,507,590,
            120,163,544,507,131,507,122,132,159,507,
            123,596,162,556,202,507,138,586,169,160,
            161,584,137,507,124,121,507,144,148,507,
            135,136,187,145,146,147,507,589,588,188,
            507,139,134,186,189,507,507,129,127,507,
            507,128,507,155,140,33,153,182,507,585,
            154,181,507,166,141,157,156,561,208,507,
            327,210,507,165,183,506,507,142,507,164,
            209,150,143,149,507,507,507,175,168,555,
            541,80,167,542,507,194,507,204,190,201,
            507,200,507,215,203,507,507,219,216,195,
            507,211,212,507,218,223,507,551,240,258,
            222,507,257,507,507,265,15,340,507,408,
            341,507,302,507,507,409,266,415,516,158,
            414,507,126,125,507,171,507,172,507,173,
            507,30,174,25,507,177,170,507,178,507,
            176,507,507,179,507,184,507,191,185,180,
            47,507,507,193,507,507,197,507,507,507,
            507,192,196,507,205,507,566,507,507,198,
            507,217,206,507,207,507,199,213,214,507,
            224,581,221,220,507,507,507,228,507,226,
            602,507,225,227,507,507,507,552,229,507,
            230,507,232,507,233,507,507,507,507,231,
            234,507,507,597,236,235,507,237,507,238,
            507,239,241,507,507,507,242,582,507,507,
            247,245,507,243,507,246,248,507,507,507,
            548,507,507,250,244,251,507,249,507,252,
            254,507,255,507,507,259,570,507,507,253,
            260,507,256,507,76,507,507,598,507,267,
            262,261,263,264,507,507,553,268,507,507,
            271,507,272,270,507,507,273,507,269,507,
            507,275,507,274,276,277,507,278,507,507,
            507,557,562,279,507,280,507,543,507,281,
            507,507,507,283,282,284,507,285,507,507,
            507,57,507,286,287,288,507,290,289,568,
            507,507,291,507,293,525,507,507,292,294,
            507,295,507,507,507,298,507,507,296,507,
            297,507,299,507,303,507,304,301,300,507,
            305,507,306,507,507,307,507,310,507,507,
            507,507,507,313,308,507,312,314,309,507,
            507,311,507,317,507,315,507,507,507,507,
            316,549,319,325,507,320,507,321,507,318,
            322,323,324,507,326,507,507,507,539,328,
            507,507,533,330,331,507,507,329,507,507,
            333,332,335,507,507,507,337,507,336,507,
            338,507,339,507,507,334,519,507,343,507,
            545,507,507,567,345,507,507,344,574,342,
            507,346,507,347,507,348,507,351,507,507,
            507,350,352,507,507,349,507,356,353,354,
            507,511,507,16,507,358,559,507,357,355,
            359,507,507,507,507,360,361,507,362,507,
            507,507,364,507,365,366,507,368,367,507,
            363,507,371,507,507,528,507,508,370,369,
            507,507,592,507,372,374,507,507,507,373,
            375,377,376,507,507,507,27,378,521,380,
            507,507,379,507,507,383,382,507,381,385,
            507,507,386,387,507,507,517,507,513,507,
            389,390,388,507,384,507,507,594,507,507,
            391,507,394,392,393,507,507,395,507,509,
            398,396,507,397,507,507,507,560,400,399,
            507,507,401,507,563,507,507,507,507,507,
            402,524,507,507,403,404,406,416,507,507,
            405,518,507,407,520,507,507,507,413,410,
            411,507,515,507,507,417,507,531,507,412,
            507,419,507,507,565,418,507,535,538,507,
            507,68,507,507,591,507,507,558,510,420,
            579,507,507,421,422,507,507,550,546,530,
            507,423,424,507,507,425,426,507,507,73,
            427,507,507,507,431,432,507,433,507,428,
            429,430,507,434,507,512,507,507,66,507,
            507,437,507,440,436,438,507,70,441,435,
            439,603,507,507,507,446,442,445,444,443,
            507,507,507,529,507,507,507,507,507,507,
            449,507,507,454,450,452,547,448,507,507,
            507,507,451,447,455,453,456,458,62,507,
            527,507,460,457,507,507,507,507,536,507,
            459,463,471,462,507,464,461,507,507,507,
            507,465,466,467,507,507,507,468,507,507,
            507,507,472,470,475,469,507,473,507,477,
            507,507,593,507,507,507,474,476,480,479,
            507,482,507,481,507,478,507,483,507,507,
            601,507,484,507,514,486,507,485,507,595,
            507,487,507,507,507,507,490,491,488,526,
            489,507,507,492,507,507,493,507,495,507,
            494,507,578,507,600,507,496,507,497,507,
            498,507,499,572,507,507,507,507,502,507,
            507,500,507,576,501,504,503,507,507,507,
            599,507,571
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    /**
     * assert(! goto_default);
     */
    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    /**
     * assert(! shift_default);
     */
    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
